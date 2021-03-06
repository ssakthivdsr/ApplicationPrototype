package com.application.internal.applicationinventoryservice.databaseintegration;

import java.sql.SQLException;
import java.util.List;
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

	public BusinessApplicationDetailsTO retrieveBusinessApplicationData(int applicationId) {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		List<UserTO> users = retrieveUsers(applicationId, template);

		List<TransactionTO> transactions = retrieveTransaction(applicationId, template);

		List<ChannelTO> channels = retrieveChannels(applicationId, template);

		List<ProductTO> products = retrieveProduct(applicationId, template);

		List<BusinessApplicationQuestionAnswerTO> businessApplicationQuestionAnswer = retrieveQuestionAnswer(
				applicationId, template);

		BusinessApplicationDetailsTO businessApplicationDetailsTO = new BusinessApplicationDetailsTO();
		businessApplicationDetailsTO.setUsers(users);
		businessApplicationDetailsTO.setChannels(channels);
		businessApplicationDetailsTO.setProducts(products);
		businessApplicationDetailsTO.setTransactions(transactions);
		businessApplicationDetailsTO.setBusinessApplicationQuestionAnswer(businessApplicationQuestionAnswer);
		return businessApplicationDetailsTO;
	}

	private List<BusinessApplicationQuestionAnswerTO> retrieveQuestionAnswer(int applicationId,
			NamedParameterJdbcTemplate template) {
		String query = "SELECT id as id, application_id as applicationId,question_id as questionId, answer FROM assessment.application_additional_details where application_id=:applicationId order by question_id asc";
		SqlParameterSource questionAnswerParam = new MapSqlParameterSource("applicationId", applicationId);
		List<BusinessApplicationQuestionAnswerTO> businessApplicationQuestionAnswer = template.query(query,
				questionAnswerParam, new BeanPropertyRowMapper<BusinessApplicationQuestionAnswerTO>(
						BusinessApplicationQuestionAnswerTO.class));
		return businessApplicationQuestionAnswer;
	}

	private List<ProductTO> retrieveProduct(int applicationId, NamedParameterJdbcTemplate template) {
		String productRetrievalQuery = "select id as id,application_id as applicationId,product_type as productType,volume as volume,null as volumeObject,written_premium_of_products as writtenPremiumOfProducts, null as writtenPremiumOfProductsObject from assessment.application_product_details where application_id=:applicationId";
		SqlParameterSource productParams = new MapSqlParameterSource("applicationId", applicationId);
		List<ProductTO> products = template.query(productRetrievalQuery, productParams,
				new BeanPropertyRowMapper<ProductTO>(ProductTO.class));
		for (ProductTO product : products) {
			PGobject productPGO1 = product.getVolume();
			PGobject productPGO2 = product.getwrittenPremiumOfProducts();
			product.setVolumeObject(new Gson().fromJson(productPGO1.getValue(), TransactionObject.class));
			product.setWrittenPremiumOfProductsObject(
					new Gson().fromJson(productPGO2.getValue(), TransactionObject.class));
		}
		return products;
	}

	private List<ChannelTO> retrieveChannels(int applicationId, NamedParameterJdbcTemplate template) {
		String channelRetrievalQuery = "select id as id,application_id as applicationId,channel_type as channelType,volume as volume, null as volumeObject from assessment.application_channel_details where application_id=:applicationId";
		SqlParameterSource channelParams = new MapSqlParameterSource("applicationId", applicationId);
		List<ChannelTO> channels = template.query(channelRetrievalQuery, channelParams,
				new BeanPropertyRowMapper<ChannelTO>(ChannelTO.class));
		for (ChannelTO channel : channels) {
			PGobject channelPGO = channel.getVolume();
			channel.setVolumeObject(new Gson().fromJson(channelPGO.getValue(), TransactionObject.class));
		}
		return channels;
	}

	private List<TransactionTO> retrieveTransaction(int applicationId, NamedParameterJdbcTemplate template) {
		String transactionRetrievalQuery = "select id as id,application_id as applicationId,transaction_type as transactionType,volume as volume, null as volumeObject from assessment.application_transaction_details where application_id=:applicationId";
		SqlParameterSource transactionParams = new MapSqlParameterSource("applicationId", applicationId);
		List<TransactionTO> transactions = template.query(transactionRetrievalQuery, transactionParams,
				new BeanPropertyRowMapper<TransactionTO>(TransactionTO.class));
		for (TransactionTO transaction : transactions) {
			PGobject transactionPGO = transaction.getVolume();
			transaction.setVolumeObject(new Gson().fromJson(transactionPGO.getValue(), TransactionObject.class));
		}
		return transactions;
	}

	private List<UserTO> retrieveUsers(int applicationId, NamedParameterJdbcTemplate template) {
		String userRetrievalQuery = "select id as id,application_id as applicationId,user_type as userType,volume as volume, null as volumeObject from assessment.application_user_details where application_id=:applicationId";
		SqlParameterSource userParams = new MapSqlParameterSource("applicationId", applicationId);
		List<UserTO> users = template.query(userRetrievalQuery, userParams,
				new BeanPropertyRowMapper<UserTO>(UserTO.class));

		for (UserTO user : users) {
			PGobject userPGO = user.getVolume();
			user.setVolumeObject(new Gson().fromJson(userPGO.getValue(), TransactionObject.class));
		}
		return users;
	}

	public void storeUpdateBusinessApplicationData(BusinessApplicationDetailsTO businessApplicationDetailsTO)
			throws SQLException {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		List<ChannelTO> channels = businessApplicationDetailsTO.getChannels();
		List<TransactionTO> transactions = businessApplicationDetailsTO.getTransactions();
		List<ProductTO> products = businessApplicationDetailsTO.getProducts();
		List<UserTO> users = businessApplicationDetailsTO.getUsers();
		List<BusinessApplicationQuestionAnswerTO> businessApplicationQuestionAnswer = businessApplicationDetailsTO
				.getBusinessApplicationQuestionAnswer();

		updateChannels(template, channels);

		updateProducts(template, products);

		updateTransaction(template, transactions);

		updateUsers(template, users);

		updateQuestions(template, businessApplicationQuestionAnswer);

		updateBusinessApplicationScore(businessApplicationDetailsTO, template);
	}

	private void updateBusinessApplicationScore(BusinessApplicationDetailsTO businessApplicationDetailsTO,
			NamedParameterJdbcTemplate template) {
		ruleEngine.setBusinessApplicationScore(businessApplicationDetailsTO);
		String updateSql = "update assessment.application_score set business_value = :businessValue, agility = :agility,business_total = :businessTotal where application_id=:applicationId";
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("businessTotal", ruleEngine.getBusinessTotal())
				.addValue("businessValue", ruleEngine.getBusinessValue()).addValue("agility", ruleEngine.getAgility())
				.addValue("applicationId",
						businessApplicationDetailsTO.getBusinessApplicationQuestionAnswer().get(0).getApplicationId());
		template.update(updateSql, parameters);
	}

	private void updateQuestions(NamedParameterJdbcTemplate template,
			List<BusinessApplicationQuestionAnswerTO> businessApplicationQuestionAnswer) {
		int count = 0;
		String sql = "select count(*) as count from assessment.application_additional_details where application_id = :applicationId";
		SqlParameterSource param = new MapSqlParameterSource("applicationId",
				businessApplicationQuestionAnswer.get(0).getApplicationId());
		count = template.queryForObject(sql, param, Integer.class);
		for (BusinessApplicationQuestionAnswerTO businessApplicationQuestionAnswers : businessApplicationQuestionAnswer) {
			if (count > 0) {
				String updateSql = "update assessment.application_additional_details set answer = :answer where application_id=:applicationId and question_id=:questionId";
				SqlParameterSource questionAnswerParams = new MapSqlParameterSource()
						.addValue("questionId", businessApplicationQuestionAnswers.getQuestionId())
						.addValue("answer", businessApplicationQuestionAnswers.getAnswer())
						.addValue("applicationId", businessApplicationQuestionAnswers.getApplicationId());
				template.update(updateSql, questionAnswerParams);
			} else {
				String AdditionalDetails = "insert into assessment.application_additional_details values(DEFAULT,:applicationId,:questionId,:answer)";
				SqlParameterSource questionAnswerParams = new MapSqlParameterSource()
						.addValue("questionId", businessApplicationQuestionAnswers.getQuestionId())
						.addValue("answer", businessApplicationQuestionAnswers.getAnswer())
						.addValue("applicationId", businessApplicationQuestionAnswers.getApplicationId());
				template.update(AdditionalDetails, questionAnswerParams);
			}
		}
	}

	private void updateUsers(NamedParameterJdbcTemplate template, List<UserTO> users) throws SQLException {
		String deleteUser = "delete from assessment.application_user_details where application_id=:id";
		SqlParameterSource userParam = new MapSqlParameterSource("id", users.get(0).getapplicationId());
		template.update(deleteUser, userParam);
		for (UserTO user : users) {
			String userSql = "insert into assessment.application_user_details values(DEFAULT,:applicationId,:userType, :volume)";
			PGobject userPGO = new PGobject();
			userPGO.setType("json");
			userPGO.setValue(user.getVolumeObject().toString());
			SqlParameterSource userParams = new MapSqlParameterSource().addValue("userType", user.getuserType())
					.addValue("applicationId", user.getapplicationId()).addValue("volume", userPGO);
			template.update(userSql, userParams);
		}
	}

	private void updateTransaction(NamedParameterJdbcTemplate template, List<TransactionTO> transactions)
			throws SQLException {
		String deleteTransaction = "delete from assessment.application_transaction_details where application_id=:id";
		SqlParameterSource transactionParam = new MapSqlParameterSource("id", transactions.get(0).getapplicationId());
		template.update(deleteTransaction, transactionParam);
		for (TransactionTO transaction : transactions) {
			String transactionSql = "insert into assessment.application_transaction_details values(DEFAULT,:applicationId,:transactionType,:volume)";
			PGobject transactionPGO = new PGobject();
			transactionPGO.setType("json");
			transactionPGO.setValue(transaction.getVolumeObject().toString());
			SqlParameterSource transactionParams = new MapSqlParameterSource()
					.addValue("transactionType", transaction.gettransactionType())
					.addValue("applicationId", transaction.getapplicationId()).addValue("volume", transactionPGO);
			template.update(transactionSql, transactionParams);
		}
	}

	private void updateProducts(NamedParameterJdbcTemplate template, List<ProductTO> products) throws SQLException {
		String deleteProduct = "delete from assessment.application_product_details where application_id=:id";
		SqlParameterSource productParam = new MapSqlParameterSource("id", products.get(0).getapplicationId());
		template.update(deleteProduct, productParam);
		for (ProductTO product : products) {
			String productSql = "insert into assessment.application_product_details values(DEFAULT,:applicationId,:productType,:volume,:writtenPremiumOfProducts)";
			PGobject productPGO1 = new PGobject();
			PGobject productPGO2 = new PGobject();
			productPGO1.setType("json");
			productPGO2.setType("json");
			productPGO1.setValue(product.getVolumeObject().toString());
			productPGO2.setValue(product.getWrittenPremiumOfProductsObject().toString());
			SqlParameterSource productParams = new MapSqlParameterSource()
					.addValue("productType", product.getproductType())
					.addValue("applicationId", product.getapplicationId()).addValue("volume", productPGO1)
					.addValue("writtenPremiumOfProducts", productPGO2);
			template.update(productSql, productParams);
		}
	}

	private void updateChannels(NamedParameterJdbcTemplate template, List<ChannelTO> channels) throws SQLException {
		String deleteChannel = "delete from assessment.application_channel_details where application_id=:id";
		SqlParameterSource channelParam = new MapSqlParameterSource("id", channels.get(0).getapplicationId());
		template.update(deleteChannel, channelParam);
		for (ChannelTO channel : channels) {
			String channelSql = "insert into assessment.application_channel_details values(DEFAULT,:applicationId,:channelType,:volume)";
			PGobject channelPGO = new PGobject();
			channelPGO.setType("json");
			channelPGO.setValue(channel.getVolumeObject().toString());
			SqlParameterSource channelParams = new MapSqlParameterSource()
					.addValue("channelType", channel.getchannelType())
					.addValue("applicationId", channel.getapplicationId()).addValue("volume", channelPGO);
			template.update(channelSql, channelParams);
		}
	}

}
