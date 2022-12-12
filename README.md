# americano
## 前言
這裡存放我學習 Java 的一些程式碼片段，說明可能不會很多。想瞭解我更多的學習筆記或想法的話，歡迎來我的網誌坐坐。  
網誌：[法蘭克的記事本](https://frankwritingnotes.blogspot.com/)
## JDBC
### Script - 讀取 SQL 腳本
剛學到 JDBC，從 Java 裡建立 SQL 指令傳入 MySQL 執行。因為不想要在 MySQL WorkBench 上寫好 DDL 後複製貼上到 IDE 裡，想要直接儲存腳本，Java 程式裡直接取用，因此我寫了這樣的靜態方法 parseCommand()，位於 [junli.jdbc.common.Script](https://github.com/JunliXiao/americano/blob/main/junli/jdbc/common/Script.java)。  
可在 CreateTableBatchNew.java 看到使用方式，和 CreateTableBatch 的原本方法比較。
