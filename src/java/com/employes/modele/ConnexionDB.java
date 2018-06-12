/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employes.modele;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import static com.employes.utils.EmployesConstantes.*;
import java.sql.PreparedStatement;

/**
 *
 * @author Jacques Augustin
 */
public class ConnexionDB {

    private String dbUrl;
    private String dbUser;
    private String dbPwd;
    private InputStream input;
    private  Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private Properties prop;
    private ClassLoader classLoader;

    public ConnexionDB() {

        classLoader = Thread.currentThread().getContextClassLoader();
        input = classLoader.getResourceAsStream(CHEMIN_FICHIER_PROPERTIES);

        prop = new Properties();

        try {
            prop.load(input);
            this.dbUrl = prop.getProperty(DB_URL);
            this.dbUser = prop.getProperty(DB_USER);
            this.dbPwd = prop.getProperty(DB_PWD);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public Statement getStatementDB() {
        try {
            conn = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPwd);
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return stmt;
    }

    public PreparedStatement getPreparedStatementDB(String req) {
        try {
            conn = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPwd);
            pstmt = conn.prepareStatement(req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return pstmt;
    }
}
