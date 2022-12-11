# americano
## 前言
這裡存放我學習 Java 的一些程式碼片段。
## JDBC
### Script
剛學到 JDBC，從 Java 裡建立 SQL 指令傳入 MySQL 執行。因為不想要在 MySQL WorkBench 寫好指令後複製貼上到 IDE 裡，想要直接儲存腳本，Java 程式裡直接取用，因此我寫了這樣的靜態方法 parseCommand()，位於套件 common。  
可在 CreateTableBatchNew.java 看到使用方式，和 CreateTableBatch 的原本方法比較。
