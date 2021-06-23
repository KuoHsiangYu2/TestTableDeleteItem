# TestTableDeleteItem

這隻簡單小專案主要是在練習透過 JavaScript 操控 DOM API，  
動態 render 更新整個 table 的資料畫面。  

由前端透過 XMLHttpRequest物件 發出請求，接收後端程式返回的資料後。  
動態更新整個table資料畫面。  
刪除指定列數資料也是一樣，在按下按鈕後，  
先去蒐集打勾的列數，接著把列數編號送到後端程式，進行軟刪除。  

```no-highlight
預前準備

1.
打開 Microsoft SQL Server Management Studio 18，
接著創建 DB08 這個 database。

2.
打開命令提示字元，
使用 『cd』 指令 切換到專案根目錄【含有CustomerTableScript.sql檔案】，
接著輸入指令 『sqlcmd -i CustomerTableScript.sql』 執行SQL指令創建table與新增預設資料。

3.
在專案根目錄創建 .\src\main\webapp\META-INF\ 資料夾，
接著在這層資料夾新增 context.xml檔案，
檔案內容如下

<?xml version="1.0" encoding="UTF-8"?>
<Context displayName="TestTableDeleteItem"
    docBase="TestTableDeleteItem" path="/TestTableDeleteItem"
    reloadable="true">
    <Resource name="/jdbc/TestTableDeleteItem" auth="Container"
        type="javax.sql.DataSource" username="『資料庫帳號名稱』" password="『資料庫帳號密碼』"
        driverClassName="com.microsoft.sqlserver.jdbc.SQLServerDriver"
        url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=DB08" maxTotal="『連線池物件最大數量』"
        maxIdle="『連線池物件預設數量』" maxWaitMillis="10000" />
</Context>
```