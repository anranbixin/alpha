import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDao {
    public void addNews(String title,String text,String pic,String time,String author)
    {
        DbUtil dbUtil = new DbUtil();
        Connection con = dbUtil.getConnection();
        String sql = "INSERT INTO news(news_title,news_text,news_picture,news_time,news_author) VALUES('"  + title + "','" + text +"','"+ pic + "','" + time +"','" + author + "')";
        try {
            Statement st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteNews(int id)
    {
        DbUtil dbUtil = new DbUtil();
        Connection con = dbUtil.getConnection();
        String sql = "DELETE FROM news where news_id = '"+ id+"'" ;
        try {
            Statement st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterNews(int nid,String title,String text,String pic,String time,String author)
    {
        DbUtil dbUtil = new DbUtil();
        Connection con = dbUtil.getConnection();
        String sql = "UPDATE news SET news_title='"+title+"',news_text='"+text+"',news_picture='"+pic+"',news_time='"+time+"',news_author='"+author+"' where news_id='"+nid+"'";
        try {
            Statement st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<News> typelist(String type) throws SQLException {
        String sql = "SELECT * FROM news where news_type = '" + type + "'";
//        String sql = "SELECT * FROM news where news_type = '1'";
        DbUtil dbUtil = new DbUtil();
        Connection con = dbUtil.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery(sql);
        List<News> news = new ArrayList();
        while (resultSet.next())
        {
            News news2 = new News();
            news2.setId(resultSet.getInt("news_id"));
            news2.setTitle(resultSet.getString("news_title"));
//            news2.setText(resultSet.getString("news_text"));
            news2.setType(resultSet.getString("news_type"));
            news.add(news2);
        }
        resultSet.close();
        ps.close();
        dbUtil.closeCon();
        return news;
    }

//
//    public ResultSet selectNews() throws SQLException {
//        DbUtil dbUtil=new DbUtil();
//        String sql="SELECT * from news";
//        Connection connection=dbUtil.getConnection();
//        PreparedStatement pst;
//        pst=connection.prepareStatement(sql);
//        ResultSet rs=pst.executeQuery(sql);
//        return rs;
//    }

    public static void main(String[] args) {
        NewsDao newsDao = new NewsDao();
        try {
            List<News> newsList = newsDao.typelist("1");
            for(int i = 0 ; i < newsList.size() ; i++) {
                System.out.println(newsList.get(i).getId());
                System.out.println(newsList.get(i).getTitle());
            }
//            for (News attribute: newsList
//                 ) {
//                System.out.println(attribute);
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
