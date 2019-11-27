import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsFind {
    //看需要返回什么，如果需要返回boolean



    public News newsFind(int id) throws SQLException {
        String sql="select * from news where news_id = '" + id +"'";
        DbUtil dbUtil = new DbUtil();
        Connection con = dbUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement ps = con.prepareStatement(sql);
        resultSet = ps.executeQuery();
        News news = new News();
        while (resultSet.next()) {
            news.setId(resultSet.getInt("news_id"));
            news.setTitle(resultSet.getString("news_title"));
            news.setText(resultSet.getString("news_text"));
            news.setTime(resultSet.getString("news_time"));
            news.setAuthor(resultSet.getString("news_author"));
        }
        resultSet.close();
        ps.close();
        dbUtil.closeCon();
        return news;
    }

    public News newsFind(String title) throws SQLException {
        String sql="select * from news where news_title = '" + title +"'";
        DbUtil dbUtil = new DbUtil();
        Connection con = dbUtil.getConnection();
        ResultSet resultSet = null;
        PreparedStatement ps = con.prepareStatement(sql);
        resultSet = ps.executeQuery();
        News news = new News();
        while (resultSet.next()) {
            news.setId(resultSet.getInt("news_id"));
            news.setTitle(resultSet.getString("news_title"));
            news.setText(resultSet.getString("news_text"));
            news.setTime(resultSet.getString("news_time"));
            news.setAuthor(resultSet.getString("news_author"));
        }
        resultSet.close();
        ps.close();
        dbUtil.closeCon();
        return news;
    }

    //test
    public static void main(String[] args) throws SQLException {
        int id = 1;
        String title = "马云讲环保";
        News news01 = new News();
        News news02 = new News();
        NewsFind newsFind = new NewsFind();
        news01 = newsFind.newsFind(id);
        news02 = newsFind.newsFind(title);


    }
}
