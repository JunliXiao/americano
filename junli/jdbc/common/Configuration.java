package junli.jdbc.common;

public class Configuration {
	// MySQL 8之後連線URL需加上SSL與時區設定
	public final static String URL = "jdbc:mysql://{Host}:{Port}/bookshop_jdbc?useUnicode=yes&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Taipei";
	public final static String USER = "root";
	public final static String PASSWORD = "{YourPassword}";
}
