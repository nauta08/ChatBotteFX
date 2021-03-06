package main.java.DAO.mapper;

import main.java.DAO.Mapper;
import main.java.model.Question;
import main.java.model.TypeReponse;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeReponseMapper implements Mapper<TypeReponse> {

    TypeReponse element  = null;
    String table = "TYPE_REPONSE";

    @Override
    public String getTable() {
        return table;
    }

    @Override
    public TypeReponse mapping(ResultSet rs) throws SQLException {
        element = new TypeReponse();
        element.setId(rs.getInt("ID"));
        element.setType(rs.getString("TYPE"));
        return element;
    }
}
