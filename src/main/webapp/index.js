'use strict';

let tableBodyObj = document.querySelector('#customerTable > tbody');
let tableData = [];

function addAllItem() {
    console.log('addAllItem()');
    /* 這段程式碼會去把 table 底下的 tbody 所有資料列 加回去。 */
    var trData = '';
    for (var i = 0, len = tableData.length; i < len; i++) {
        trData = trData + '<tr align="center">';
        trData = trData + '<td>' + '<input type="checkbox" name="userId" value="' + tableData[i].pk + '" />' + '</td>';
        trData = trData + '<td>' + tableData[i].pk + '</td>';
        trData = trData + '<td>' + tableData[i].cName + '</td>';
        trData = trData + '<td>' + tableData[i].cAge + '</td>';
        trData = trData + '<td>' + tableData[i].cType + '</td>';
        trData = trData + '</tr>';
    }
    jQuery('#customerTable > tbody').append(trData);
}

function deleteItem() {
    console.log('deleteItem()');
    /* 這段程式碼會去把 table 底下的 tbody 所有資料列 刪光光。 */

    // JavaScript 添加或刪除子節點、子元素
    // https://blog.csdn.net/fanxiangru999/article/details/71630139

    // JavaScript刪除子節點的方法
    // https://blog.csdn.net/tegwy/article/details/4179173

    var tableBodyChildsNodeObj = tableBodyObj.childNodes;
    for (var i = tableBodyChildsNodeObj.length - 1; i >= 0; i--) {
        tableBodyObj.removeChild(tableBodyChildsNodeObj[i]);
    }

    addAllItem();
}

function fetchCustomerTableData() {
    /* 這段程式碼會去跟後端資料要『顧客資料』，接收到資料後， */
    /* 再去重新 render網頁，把『顧客資料』填入 table tbody裡面。 */

    /* 使用 AJAX技術 發送 POST請求 呼叫後端API， */
    /* 接著去接收後端API回傳給前端的資料。 */
    var xmlHttpRequest = new XMLHttpRequest();
    xmlHttpRequest.onload = function() {
        var data = xmlHttpRequest.responseText;
        console.log('xmlHttpRequest data');
        console.log(data);
        tableData = JSON.parse(data);
        deleteItem();
    }
    xmlHttpRequest.open('post', 'FetchCustomerData', true);
    xmlHttpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xmlHttpRequest.setRequestHeader('If-Modified-Since', '0');
    xmlHttpRequest.setRequestHeader('Pragma', 'no-cache');
    xmlHttpRequest.send();
}

var divStr = '';
for (var i = 1; i <= 3; i++) {
    divStr = divStr + '<br />';
}
var makeHeightObj = document.getElementById('makeHeight');
makeHeightObj.innerHTML = divStr;

var deleteButtonObj = document.getElementById('deleteButton');
deleteButtonObj.addEventListener('click', function(event) {
    /* 當使用者按下『刪除選中資料』按鈕後， */
    /* 執行這段程式，去抓出使用者有勾選刪除的項目 */
    console.log('deleteButtonObj click');
    var resultArray = [];
    var userIdArray = document.getElementsByName('userId');
    for (var i = 0, len = userIdArray.length; i < len; i++) {
        var unit = userIdArray.item(i);
        if (true === unit.checked) {
            resultArray.push(unit.value);
        }
    }

    /* 下一個步驟，透過 AJAX 把要刪除的會員id打到後端去。 */
    var xmlHttpRequest2 = new XMLHttpRequest();
    xmlHttpRequest2.onload = function() {
        var data = xmlHttpRequest2.responseText;
        console.log('xmlHttpRequest2 data');
        console.log(data);
        fetchCustomerTableData();
    }

    xmlHttpRequest2.open('post', 'DeleteCustomerById', true);
    xmlHttpRequest2.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xmlHttpRequest2.setRequestHeader('If-Modified-Since', '0');
    xmlHttpRequest2.setRequestHeader('Pragma', 'no-cache');

    /* 把陣列資料轉成 JSON 打到後端去。 */
    console.log('resultArray');
    console.log(JSON.stringify(resultArray));
    xmlHttpRequest2.send(JSON.stringify(resultArray));
});

var resetButtonObj = document.getElementById('resetButton');
resetButtonObj.addEventListener('click', function(event) {
    console.log('resetButtonObj click');
    var xmlHttpRequest3 = new XMLHttpRequest();
    xmlHttpRequest3.onload = function() {
        var data = xmlHttpRequest3.responseText;
        console.log('xmlHttpRequest3 data');
        console.log(data);
        fetchCustomerTableData();
    }

    xmlHttpRequest3.open('post', 'ResetCustomerData', true);
    xmlHttpRequest3.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xmlHttpRequest3.setRequestHeader('If-Modified-Since', '0');
    xmlHttpRequest3.setRequestHeader('Pragma', 'no-cache');
    xmlHttpRequest3.send();
});

/* 載入頁面後，透過 AJAX 開始跟後端要資料，render整個畫面。 */
fetchCustomerTableData();
