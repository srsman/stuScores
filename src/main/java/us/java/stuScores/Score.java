package us.java.stuScores;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static us.java.stuScores.JDBC.createStatement;

/**
 * Created by 15437 on 2016/11/21.
 */
@XmlRootElement
public class Score {
    public long id;
    public long student;
    public long exam;
    public int score;

    public Score(){}

    public Score(long id, long student_id, long exam_id, int score){
        this.id = id;
        this.student = student_id;
        this.exam = exam_id;
        this.score = score;
    }

    public static Score[] GetAllScore(){
        try {
            Statement statement = createStatement();
            ResultSet r = statement.executeQuery("SELECT COUNT (*) AS rowcount FROM score;");
            r.next();
            int rows = r.getInt("rowcount");
            r.close();
            ResultSet rs = statement.executeQuery("SELECT * FROM score;");
            Score score[] = new Score[rows];
            int i = 0;
            while (rs.next()){
                score[i++] = new Score(rs.getLong("id"), rs.getLong("student_id"), rs.getLong("exam_id"), rs.getInt("score"));
            }
            return score;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
