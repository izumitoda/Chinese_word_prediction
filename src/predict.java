import program.Sqlconnect;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.BaseAnalysis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.util.List;

@WebServlet("/predict")
public class predict extends HttpServlet{
    String[] lst={"one","two","three","four","five","six","seven","eight","nine","ten"};
    String[] uni={"的","在","了","是","和","一","有","日","月","也"};
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String str=req.getParameter("str");
            //str= new String(str.getBytes("ISO-8859-1"), "UTF-8");
            System.out.println("test");
            Result result= BaseAnalysis.parse(str);
            List<Term> terms=result.getTerms();
            String fenci="";
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            for(int i=0;i<terms.size();i++)
            {
                fenci+=terms.get(i).getName()+'/';
            }
            out.write("<li >分词结果：" + fenci + "</li>");
            out.write("<li>预测结果：</li>");
            if(terms.size()>=2) {
                String two = terms.get(terms.size() - 1).getName();
                String one = terms.get(terms.size() - 2).getName();
                Connection con = new Sqlconnect().TheSqlConnection();
                String finalresult = Sqlconnect.SearchNgram("select second from trigram where first='" + one + " " + two + "'");
                if (finalresult.length() != 0) {
                    finalresult = finalresult.replaceAll(String.valueOf((char) 160), " ").trim();
                    String[] fresult = finalresult.split("\\s");
                    int len = fresult.length;
                    for (int i = 0; i < len; i++) {
                        out.write("<li onclick=set_item() id=\""+lst[i]+"\">" + String.valueOf(i + 1) + ":" + fresult[len - i - 1] + "</li>");
                    }
                    if (len != 10) {
                        finalresult = Sqlconnect.SearchNgram("select two from bigram where one='" + two + "'");

                            finalresult = finalresult.replaceAll(String.valueOf((char) 160), " ").trim();
                            System.out.println(finalresult);
                            String[] ffresult = finalresult.split(" ");

                            int cot = len;
                            for (int i = 0; i < ffresult.length && cot < 10; i++) {
                                int flag = 0;
                                for (int j = 0; j < fresult.length; j++) {
                                    if (ffresult[ffresult.length - i - 1].equals(fresult[j])) {

                                        flag = 1;
                                        break;
                                    }
                                }
                                if (flag == 0) {
                                    out.write("<li>" + String.valueOf(cot + 1) + ":" + ffresult[ffresult.length - i - 1] + "</li>");
                                    cot++;
                                }


                        }
                    }
                }
                else {

                    int len=0;
                    finalresult = Sqlconnect.SearchNgram("select two from bigram where one='" + two + "'");
                    if(finalresult.length()==0)
                    {
                        int cot=len;
                        for (int i = 0; i <uni.length && cot<10; i++) {
                            out.write("<li>" + String.valueOf(cot+ 1) + ":" + uni[i] + "</li>");
                            cot++;
                        }
                    }
                    else {
                        finalresult = finalresult.replaceAll(String.valueOf((char) 160), " ").trim();
                        System.out.println(finalresult);
                        String[] ffresult = finalresult.split(" ");
                        for (int i = 0; i < 10 - len; i++) {

                            out.write("<li>" + String.valueOf(i + len + 1) + ":" + ffresult[ffresult.length - i - 1] + "</li>");
                        }
                    }
                }
                con.close();
            }
            else
            {
                Connection cnn = new Sqlconnect().TheSqlConnection();
                int len=0;
                String finalresult = Sqlconnect.SearchNgram("select two from bigram where one='" +terms.get(0).getName()+ "'");

                    if(finalresult.length()==0)
                    {
                        int cot=len;
                        for (int i = 0; i <uni.length && cot<10; i++) {
                                out.write("<li>" + String.valueOf(cot+ 1) + ":" + uni[i] + "</li>");
                                cot++;
                        }

                    }
                    else {
                        finalresult = finalresult.replaceAll(String.valueOf((char) 160), " ").trim();
                        System.out.println(finalresult);
                        String[] fresult = finalresult.split(" ");
                        for (int i = 0; i < 10 - len; i++) {
                            out.write("<li>" + String.valueOf(i + len + 1) + ":" + fresult[fresult.length - i - 1] + "</li>");
                        }
                    }
                cnn.close();
            }
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
