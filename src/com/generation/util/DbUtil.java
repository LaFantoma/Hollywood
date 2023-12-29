package com.generation.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil 
{

    public static Connection connectToDB(String dbName) throws Exception
    {
        String versione = "com.mysql.cj.jdbc.Driver";
        Class.forName(versione);   
        //                       diciamo che vogliamo utilizzare un db mysql
        //                                      questo è il server
        //                                                   questo è il db (use primodbmysql)
        //                                                                mandiamo come parametri username e password
        String dbInformations = "jdbc:mysql://localhost:3306/"+dbName+"?user=jaita&password=jaita107";
        Connection tubo = DriverManager.getConnection(dbInformations);
        return tubo;
    }


    //public e static
    //public perchè sarà usato FUORI
    //static perchè voglio sia come quelli di Console
    //Vale a dire possa usarlo direttamente chiamando la Classe e non un suo OGGETTO
    //Console c = new Console();
    //c.print("ciao");
    //Console.print("ciao");

    //Metodino salvavita
    //unico compito, ENTRA UNA STRINGA
    //esce quella Stringa con gli apici prima e dopo
    public static String quota(String in)
    {
        return "'"+in+"'";
    }
}
