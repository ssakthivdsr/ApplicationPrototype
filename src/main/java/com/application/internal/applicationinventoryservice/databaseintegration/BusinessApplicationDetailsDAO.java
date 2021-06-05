package com.application.internal.applicationinventoryservice.databaseintegration;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.application.internal.applicationinventoryservice.scoreengine.BusinessApplicationRuleEngine;
import com.application.internal.applicationinventoryservice.to.BusinessApplicationDetailsTO;
import com.application.internal.applicationinventoryservice.to.ChannelTO;
import com.application.internal.applicationinventoryservice.to.TransactionObject;
import com.application.internal.applicationinventoryservice.to.ProductTO;
import com.application.internal.applicationinventoryservice.to.TransactionTO;
import com.application.internal.applicationinventoryservice.to.UserTO;
import com.application.internal.applicationinventoryservice.to.BusinessApplicationQuestionAnswerTO;

import com.google.gson.Gson;

@Component
public class BusinessApplicationDetailsDAO {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private BusinessApplicationRuleEngine ruleEngine;

	@SuppressWarnings({ "unchecked", "rawtypes"})
	public BusinessApplicationDetailsTO retrieveBusinessApplicationData(int applicationId) {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String userRetrievalQuery = "select id as id,application_id as applicationId,user_type as userType,volume as volume, null as volumeObject from assessment.application_user_details where application_id=:applicationId";
		Map userParams = new HashMap();
		userParams.put("applicationId", applicationId);
		List<UserTO> users = template.query(userRetrievalQuery, userParams, new BeanPropertyRowMapper(UserTO.class));

		String transactionRetrievalQuery = "select id as id,application_id as applicationId,transaction_type as transactionType,volume as volume, null as volumeObject from assessment.application_transaction_details where application_id=:applicationId";
		Map transactionParams = new HashMap();
		transactionParams.put("applicationId", applicationId);
		List<TransactionTO> transactions = template.query(transactionRetrievalQuery, transactionParams,
				new BeanPropertyRowMapper(TransactionTO.class));
		for (int i = 0; i < transactions.size(); i++) {
			PGobject transactionPGO = transactions.get(i).getVolume();
			transactions.get(i)
					.setVolumeObject(new Gson().fromJson(transactionPGO.getValue(), TransactionObject.class));
		}

		String channelRetrievalQuery = "select id as id,application_id as applicationId,channel_type as channelType,volume as volume, null as volumeObject from assessment.application_channel_details where application_id=:applicationId";
		Map channelParams = new HashMap();
		channelParams.put("applicationId", applicationId);
		List<ChannelTO> channels = template.query(channelRetrievalQuery, channelParams,
				new BeanPropertyRowMapper(ChannelTO.class));
		for (int i = 0; i < channels.size(); i++) {
			PGobject channelPGO = channels.get(i).getVolume();
			channels.get(i).setVolumeObject(new Gson().fromJson(channelPGO.getValue(), TransactionObject.class));
		}

		String productRetrievalQuery = "select id as id,application_id as applicationId,product_type as productType,volume as volume,null as volumeObject,written_premium_of_products as writtenPremiumOfProducts, null as writtenPremiumOfProductsObject from assessment.application_product_details where application_id=:applicationId";
		Map productParams = new HashMap();
		productParams.put("applicationId", applicationId);
		List<ProductTO> products = template.query(productRetrievalQuery, productParams,
				new BeanPropertyRowMapper(ProductTO.class));
		for (int i = 0; i < products.size(); i++) {
			PGobject productPGO1 = products.get(i).getVolume();
			PGobject productPGO2 = products.get(i).getwrittenPremiumOfProducts();
			products.get(i).setVolumeObject(new Gson().fromJson(productPGO1.getValue(), TransactionObject.class));
			products.get(i).setWrittenPremiumOfProductsObject(
					new Gson().fromJson(productPGO2.getValue(), TransactionObject.class));
		}

		String query = "SELECT id as id, application_id as applicationId,question_id as questionId, answer FROM assessment.application_additional_details where application_id=:applicationId order by question_id asc";
		SqlParameterSource param = new MapSqlParameterSource("applicationId", applicationId);
		List<BusinessApplicationQuestionAnswerTO> businessApplicationQuestionAnswer = template.query(query, param,
				new BeanPropertyRowMapper(BusinessApplicationQuestionAnswerTO.class));
		for (int i = 0; i < users.size(); i++) {
			PGobject userPGO = users.get(i).getVolume();
			users.get(i).setVolumeObject(new Gson().fromJson(userPGO.getValue(), TransactionObject.class));
		}

		BusinessApplicationDetailsTO businessApplicationDetailsTO = new BusinessApplicationDetailsTO();
		businessApplicationDetailsTO.setUsers(users);
		businessApplicationDetailsTO.setChannels(channels);
		businessApplicationDetailsTO.setProducts(products);
		businessApplicationDetailsTO.setTransactions(transactions);
		businessApplicationDetailsTO.setBusinessApplicationQuestionAnswer(businessApplicationQuestionAnswer);
		return businessApplicationDetailsTO;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void storeUpdateBusinessApplicationData(BusinessApplicationDetailsTO businessApplicationDetailsTO)
			throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		List<ChannelTO> channels = businessApplicationDetailsTO.getChannels();
		List<TransactionTO> transactions = businessApplicationDetailsTO.getTransactions();
		List<ProductTO> products = businessApplicationDetailsTO.getProducts();
		List<UserTO> users = businessApplicationDetailsTO.getUsers();
		List<BusinessApplicationQuestionAnswerTO> businessApplicationQuestionAnswer = businessApplicationDetailsTO
				.getBusinessApplicationQuestionAnswer();

		String deleteChannel = "delete from assessment.application_channel_details where application_id=:id";
		SqlParameterSource channelParam = new MapSqlParameterSource("id", channels.get(0).getapplicationId());
		template.update(deleteChannel, channelParam);
		for (int i = 0; i < channels.size(); i++) {
			String channelSql = "insert into assessment.application_channel_details values(DEFAULT,:applicationId,:channelType,:volume)";
			Map channelParams = new HashMap();
			channelParams.put("channelType", channels.get(i).getchannelType());
			channelParams.put("applicationId", channels.get(i).getapplicationId());
			PGobject channelPGO = new PGobject();
			channelPGO.setType("json");
			channelPGO.setValue(channels.get(i).getVolumeObject().toString());
			channelParams.put("volume", channelPGO);
			template.update(channelSql, channelParams);
		}

		String deleteProduct = "delete from assessment.application_product_details where application_id=:id";
		SqlParameterSource productParam = new MapSqlParameterSource("id", products.get(0).getapplicationId());
		template.update(deleteProduct, productParam);
		for (int i = 0; i < products.size(); i++) {
			String productSql = "insert into assessment.application_product_details values(DEFAULT,:applicationId,:productType,:volume,:writtenPremiumOfProducts)";
			Map productParams = new HashMap();
			productParams.put("productType", products.get(i).getproductType());
			productParams.put("applicationId", products.get(i).getapplicationId());
			PGobject productPGO1 = new PGobject();
			PGobject productPGO2 = new PGobject();
			productPGO1.setType("json");
			productPGO2.setType("json");
			productPGO1.setValue(products.get(i).getVolumeObject().toString());
			productPGO2.setValue(products.get(i).getWrittenPremiumOfProductsObject().toString());
			productParams.put("volume", productPGO1);
			productParams.put("writtenPremiumOfProducts", productPGO2);
			template.update(productSql, productParams);
		}

		String deleteTransaction = "delete from assessment.application_transaction_details where application_id=:id";
		SqlParameterSource transactionParam = new MapSqlParameterSource("id", transactions.get(0).getapplicationId());
		template.update(deleteTransaction, transactionParam);
		for (int i = 0; i < transactions.size(); i++) {
			String transactionSql = "insert into assessment.application_transaction_details values(DEFAULT,:applicationId,:transactionType,:volume)";
			Map transactionParams = new HashMap();
			transactionParams.put("transactionType", transactions.get(i).gettransactionType());
			transactionParams.put("applicationId", transactions.get(i).getapplicationId());
			PGobject transactionPGO = new PGobject();
			transactionPGO.setType("json");
			transactionPGO.setValue(transactions.get(i).getVolumeObject().toString());
			transactionParams.put("volume", transactionPGO);
			template.update(transactionSql, transactionParams);
		}

		String deleteUser = "delete from assessment.application_user_details where application_id=:id";
		SqlParameterSource userParam = new MapSqlParameterSource("id", users.get(0).getapplicationId());
		template.update(deleteUser, userParam);
		for (int i = 0; i < users.size(); i++) {
			String userSql = "insert into assessment.application_user_details values(DEFAULT,:applicationId,:userType, :volume)";
			Map userParams = new HashMap();
			userParams.put("applicationId", users.get(i).getapplicationId());
			userParams.put("userType", users.get(i).getuserType());
			PGobject userPGO = new PGobject();
			userPGO.setType("json");
			userPGO.setValue(users.get(i).getVolumeObject().toString());
			userParams.put("volume", userPGO);
			template.update(userSql, userParams);
		}

		int count = 0;
		String sql = "select count(*) as count from assessment.application_additional_details where application_id = :applicationId";
		SqlParameterSource param = new MapSqlParameterSource("applicationId",
				businessApplicationQuestionAnswer.get(0).getApplicationId());
		count = template.queryForObject(sql, param, Integer.class);
		for (int i = 0; i < businessApplicationQuestionAnswer.size(); i++) {
			if (count > 0) {
				String updateSql = "update assessment.application_additional_details set answer = :answer where application_id=:applicationId and question_id=:questionId";
				SqlParameterSource parameters = new MapSqlParameterSource()
						.addValue("questionId", businessApplicationQuestionAnswer.get(i).getQuestionId())
						.addValue("answer", businessApplicationQuestionAnswer.get(i).getAnswer())
						.addValue("applicationId", businessApplicationQuestionAnswer.get(i).getApplicationId());
				template.update(updateSql, parameters);
			}

			else {
				String AdditionalDetails = "insert into assessment.application_additional_details values(DEFAULT,:applicationId,:questionId,:answer)";
				Map params = new HashMap();
				params.put("applicationId", businessApplicationQuestionAnswer.get(i).getApplicationId());
				params.put("questionId", businessApplicationQuestionAnswer.get(i).getQuestionId());
				params.put("answer", businessApplicationQuestionAnswer.get(i).getAnswer());
				template.update(AdditionalDetails, params);
			}
		}

		ruleEngine.setBusinessApplicationScore(businessApplicationDetailsTO);
		count = 0;
		String scoreSql = "select count(*) as count from assessment.application_score where application_id = :applicationId";
		SqlParameterSource scoreParam = new MapSqlParameterSource("applicationId",
				businessApplicationDetailsTO.getBusinessApplicationQuestionAnswer().get(0).getApplicationId());
		count = template.queryForObject(scoreSql, scoreParam, Integer.class);
		String updateSql = "update assessment.application_score set business_value = :businessValue, agility = :agility,business_total = :businessTotal where application_id=:applicationId";
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("businessTotal", ruleEngine.getBusinessTotal())
				.addValue("businessValue", ruleEngine.getBusinessValue()).addValue("agility", ruleEngine.getAgility())
				.addValue("applicationId",
						businessApplicationDetailsTO.getBusinessApplicationQuestionAnswer().get(0).getApplicationId());
		template.update(updateSql, parameters);
	}

}
