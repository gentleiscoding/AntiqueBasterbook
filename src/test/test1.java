package test;

import java.text.SimpleDateFormat;
import java.util.Date;

import db.DBConn;
import pojo.Login;
import pojo.Posting;
import pojo.Signup;
public class test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Signup signup = new Signup();
		Login login = new Login();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createtime = df.format(new Date());
		System.out.println(createtime);
		Posting posting= new Posting("iamhaha", "haha", "java2",
				"unknow", "i wanna sell this book ", "java", "ok", "book",
				createtime,createtime);
		String a="";
		a=posting.result();
		System.out.println(a);
	}

}
