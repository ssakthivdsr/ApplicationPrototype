package com.application.internal.applicationinventoryservice.databaseintegration;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.application.internal.applicationinventoryservice.to.BusinessApplicationDetailsTO;
import com.application.internal.applicationinventoryservice.to.ChannelTO;
import com.application.internal.applicationinventoryservice.to.ProductTO;
import com.application.internal.applicationinventoryservice.to.TransactionTO;
import com.application.internal.applicationinventoryservice.to.UserTO;
import com.application.internal.applicationinventoryservice.to.BusinessApplicationQuestionAnswerTO;

@Component
public class BusinessApplicationDetailsDAO {

	@Autowired
	private DataSource dataSource;

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public BusinessApplicationDetailsTO retrieveBusinessApplicationData(int applicationId) {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String userRetrievalQuery = "select id as id,application_id as applicationId,user_type as userType,volume as volume from assessment.application_user_details where application_id=:applicationId";
		Map userParams = new HashMap();
		userParams.put("applicationId", applicationId);
		List<UserTO> userTOs = template.query(userRetrievalQuery, userParams, new BeanPropertyRowMapper(UserTO.class));

		String transactionRetrievalQuery = "select id as id,application_id as applicationId,transaction_type as transactionType,volume as volume from assessment.application_transaction_details where application_id=:applicationId";
		Map transactionParams = new HashMap();
		transactionParams.put("applicationId", applicationId);
		List<TransactionTO> transactionTOs = template.query(transactionRetrievalQuery, transactionParams,
				new BeanPropertyRowMapper(TransactionTO.class));

		String channelRetrievalQuery = "select id as id,application_id as applicationId,channel_type as channelType,volume as volume from assessment.application_channel_details where application_id=:applicationId";
		Map channelParams = new HashMap();
		channelParams.put("applicationId", applicationId);
		List<ChannelTO> channelTOs = template.query(channelRetrievalQuery, channelParams,
				new BeanPropertyRowMapper(ChannelTO.class));

		String productRetrievalQuery = "select id as id,application_id as applicationId,product_type as productType,volume as volume,written_premium_of_products as writtenPremiumOfProducts from assessment.application_product_details where application_id=:applicationId";
		Map productParams = new HashMap();
		productParams.put("applicationId", applicationId);
		List<ProductTO> productTOs = template.query(productRetrievalQuery, productParams,
				new BeanPropertyRowMapper(ProductTO.class));

		String query = "SELECT id as id, application_id as applicationId,question_id as questionId, answer FROM assessment.application_additional_details where application_id=:applicationId";
		SqlParameterSource param = new MapSqlParameterSource("applicationId", applicationId);
		List<BusinessApplicationQuestionAnswerTO> businessApplicationQuestionAnswerTOs = template.query(query, param,
				new BeanPropertyRowMapper(BusinessApplicationQuestionAnswerTO.class));

		BusinessApplicationDetailsTO businessApplicationDetailsTO = new BusinessApplicationDetailsTO();
		businessApplicationDetailsTO.setUserTOs(userTOs);
		businessApplicationDetailsTO.setChannelTOs(channelTOs);
		businessApplicationDetailsTO.setProductTOs(productTOs);
		businessApplicationDetailsTO.setTransactionTOs(transactionTOs);
		businessApplicationDetailsTO.setBusinessApplicationQuestionAnswerTOs(businessApplicationQuestionAnswerTOs);
		return businessApplicationDetailsTO;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void storeBusinessApplicationData(BusinessApplicationDetailsTO businessApplicationDetailsTO)
			throws SQLException {
		List<ChannelTO> channelTOs = businessApplicationDetailsTO.getChannelTOs();
		List<TransactionTO> transactionTOs = businessApplicationDetailsTO.getTransactionTOs();
		List<ProductTO> productTOs = businessApplicationDetailsTO.getProductTOs();
		List<UserTO> userTOs = businessApplicationDetailsTO.getUserTOs();
		List<BusinessApplicationQuestionAnswerTO> businessApplicationQuestionAnswerTOs = businessApplicationDetailsTO
				.getBusinessApplicationQuestionAnswerTOs();
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);

		for (int i = 0; i < channelTOs.size(); i++) {
			String channelSql = "insert into assessment.application_channel_details values(DEFAULT,:applicationId,:channelType,:volume)";
			Map channelParams = new HashMap();
			channelParams.put("channelType", channelTOs.get(i).getchannelType());
			channelParams.put("applicationId", channelTOs.get(i).getapplicationId());
			channelParams.put("volume", channelTOs.get(i).getVolume());
			template.update(channelSql, channelParams);
		}

		for (int i = 0; i < productTOs.size(); i++) {
			String productSql = "insert into assessment.application_product_details values(DEFAULT,:applicationId,:productType,:volume,:writtenPremiumOfProducts)";
			Map productParams = new HashMap();

			productParams.put("productType", productTOs.get(i).getproductType());
			productParams.put("applicationId", productTOs.get(i).getapplicationId());
			productParams.put("volume", productTOs.get(i).getVolume());
			productParams.put("writtenPremiumOfProducts", productTOs.get(i).getwrittenPremiumOfProducts());
			template.update(productSql, productParams);
		}

		for (int i = 0; i < transactionTOs.size(); i++) {
			String transactionSql = "insert into assessment.application_transaction_details values(DEFAULT,:applicationId,:transactionType,:volume)";
			Map transactionParams = new HashMap();

			transactionParams.put("transactionType", transactionTOs.get(i).gettransactionType());
			transactionParams.put("applicationId", transactionTOs.get(i).getapplicationId());
			transactionParams.put("volume", transactionTOs.get(i).getVolume());
			template.update(transactionSql, transactionParams);
		}

		for (int i = 0; i < userTOs.size(); i++) {
			String userSql = "insert into assessment.application_user_details values(DEFAULT,:applicationId,:userType, :volume)";

			Map userParams = new HashMap();
			userParams.put("applicationId", userTOs.get(i).getapplicationId());
			userParams.put("userType", userTOs.get(i).getuserType());
			userParams.put("volume", userTOs.get(i).getVolume());
			template.update(userSql, userParams);
		}

		for (int i = 0; i < businessApplicationQuestionAnswerTOs.size(); i++) {
			String AdditionalDetails = "insert into assessment.application_additional_details values(DEFAULT,:applicationId,:questionId,:businessAppAdditionalAnswers)";
			Map params = new HashMap();
			params.put("applicationId", businessApplicationQuestionAnswerTOs.get(i).getApplicationId());
			params.put("questionId", businessApplicationQuestionAnswerTOs.get(i).getQuestionId());
			if (businessApplicationQuestionAnswerTOs.get(i).getAnswer().length() != 0) {
				params.put("businessAppAdditionalAnswers", businessApplicationQuestionAnswerTOs.get(i).getAnswer());
			}
			template.update(AdditionalDetails, params);
		}
	}

}
