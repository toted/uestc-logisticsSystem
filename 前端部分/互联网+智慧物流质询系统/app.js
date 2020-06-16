//app.js
App({
  onLaunch: function () {

  },
  globalData: {
    userInfo: null,
    session: null,
    listData:null,
    userId:null,
  },
  //底部  
  editTabBar: function () {
    //使用getCurrentPages可以获取当前加载中所有的页面对象的一个数组，数组最后一个就是当前页面。
    var curPageArr = getCurrentPages();    //获取加载的页面
    var curPage = curPageArr[curPageArr.length - 1];    //获取当前页面的对象
    var pagePath = curPage.route;    //当前页面url
    if (pagePath.indexOf('/') != 0) {
      pagePath = '/' + pagePath;
    }
    var tabBar = this.globalData.tabBar;
    for (var i = 0; i < tabBar.list.length; i++) {
      tabBar.list[i].active = false;
      if (tabBar.list[i].pagePath == pagePath) {
        tabBar.list[i].active = true;    //根据页面地址设置当前页面状态    
      }
    }
    curPage.setData({
      tabBar: tabBar
    });
  },
  globalData: {
    //底部导航栏显示
    tabBar: {
      "color": "#9E9E9E",
      "selectedColor": "#f00",
      "backgroundColor": "#fff",
      "borderStyle": "#ccc",
      "list": [
        {
          "pagePath": "/pages/myIndex/myIndex",
          "text": "物品填写",
          "iconPath": "/images/11.png",
          "selectedIconPath": "/images/11.png",
          "clas": "menu-item",
          "selectedColor": "#88b4f7",
          active: true
        },
        {
          "pagePath": "/pages/goodsList/goodsList",
          "text": "我的物流",
          "iconPath": "/images/22.png",
          "selectedIconPath": "/images/22.png",
          "selectedColor": "#88b4f7",
          "clas": "menu-item",
          active: false
        },
        {
          "pagePath": "/pages/nodeList/nodeList",
          "text": "节点信息",
          "iconPath": "/images/33.png",
          "selectedIconPath": "/images/33.png",
          "selectedColor": "#88b4f7",
          "clas": "menu-item",
          active: false
        }
      ],
      "position": "bottom"
    },
  },
})