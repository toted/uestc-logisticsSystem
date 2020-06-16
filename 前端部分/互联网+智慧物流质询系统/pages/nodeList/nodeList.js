// pages/goodsList/goodsList.js
const app = getApp()
const url = require('../../utils/url.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    listData: [
      { "code": "1", "text": "北京", "type": "是" },
      { "code": "2", "text": "郑州", "type": "否" },
      { "code": "3", "text": "济南", "type": "是" },
      { "code": "4", "text": "西安", "type": "否" },
      { "code": "5", "text": "重庆", "type": "是" },
      { "code": "6", "text": "成都", "type": "否" }
    ]
  },
  showtext: function (e) {
    console.log(e.currentTarget.dataset.lockerid)
    wx.navigateTo({
      url: '../nodeInfo/nodeInfo?nodeNum=' + e.currentTarget.dataset.lockerid
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
   
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    app.editTabBar();    //显示自定义的底部导航
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})