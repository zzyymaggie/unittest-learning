package com.example.dbunit.dao;

import com.example.dbunit.dao.UserDao;
import com.example.dbunit.entity.User;
import org.dbunit.Assertion;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.sql.DataSource;
import java.io.File;
import java.net.MalformedURLException;

@ContextConfiguration(locations = { "classpath:testApplicationContext.xml" })
@Rollback(value=true)
public class UserDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private UserDao userDao;

    @Autowired
    private DataSource dataSource;

    private IDatabaseConnection conn;

    @Before
    public void initDbunit() throws Exception {
        conn = new DatabaseConnection(DataSourceUtils.getConnection(dataSource));
    }

    private IDataSet getDataSet(File file) throws MalformedURLException, DataSetException {
        FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);
        IDataSet dataSet = builder.build(file);
        return dataSet;
    }

    @Test
    public void saveUser() throws Exception {
        User user = new User();
        user.setNick("user001");
        user.setPassword("password001");
        userDao.save(user);

        QueryDataSet actual = new QueryDataSet(conn);
        actual.addTable("user",
                "select * from user where user.nick = 'user001'");

        IDataSet expected = getDataSet(new ClassPathResource(
                "com/example/dbunit/dao/user001.xml").getFile());

        Assertion.assertEquals(expected, actual);
    }

    @Test
    public void updateUser() throws Exception {

        IDataSet origen = getDataSet(new ClassPathResource(
                "com/example/dbunit/dao/user001.xml").getFile());

        DatabaseOperation.INSERT.execute(conn, origen);

        User user = new User();
        user.setNick("user001");
        user.setPassword("password002");
        userDao.update(user);

        QueryDataSet actual = new QueryDataSet(conn);
        actual.addTable("user",
                "select * from user where user.nick = 'user001'");

        IDataSet expected = getDataSet(new ClassPathResource(
                "com/example/dbunit/dao/user001_updated.xml").getFile());

        Assertion.assertEquals(expected, actual);
    }

    @Test
    public void removeUser() throws Exception {
        IDataSet origen = getDataSet(new ClassPathResource(
                "com/example/dbunit/dao/user001.xml").getFile());
        DatabaseOperation.INSERT.execute(conn, origen);

        userDao.remove("user001");

        QueryDataSet actual = new QueryDataSet(conn);
        actual.addTable("user", "select * from user where nick = 'user001'");

        Assert.assertEquals(0, actual.getTable("user").getRowCount());

    }

    @Test
    public void findUser() throws Exception {
        IDataSet data = getDataSet(new ClassPathResource(
                "com/example/dbunit/dao/user001.xml").getFile());
        DatabaseOperation.INSERT.execute(conn, data);


        User user = userDao.getUserByNick("user001");

        Assert.assertEquals("password001", user.getPassword());
    }

}