// pages/goodsList/goodsList.js
const app = getApp()
const url = require('../../utils/url.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    listData: ''
  },
  showtext: function (e) {
    console.log(e.currentTarget.dataset.lockerid)
    wx.navigateTo({
      url: '../logistics/logistics?wuliuNum=' + e.currentTarget.dataset.lockerid
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options.nodeNum)
    var that = this
    wx.request({
      url: url.url.getNodeInfo,
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      data: {
        'token': 'UZIYONGYUANDESHEN',
        "nodeNum": options.nodeNum
      },
      success: function (res) {
        if (res.data.code == 0) {
          that.setData({
            listData: res.data.info
          })
          wx.showToast({
            title: '查询成功!',
            icon: 'success',
            duration: 2000
          })
        }
        if (res.data.code == 1) {
          wx.showToast({
            title: '参数错误！',
            icon: 'fail',
            image: '/images/fail.png',
            duration: 2000
          })
        }
        if (res.data.code == 2) {
          wx.showToast({
            title: '验证错误！',
            icon: 'fail',
            image: '/images/fail.png',
            duration: 2000
          })
        }
        if (res.data.code == 3) {
          wx.showToast({
            title: '节点不存在！',
            icon: 'fail',
            image: '/images/fail.png',
            duration: 2000
          })
        }
      }
    })
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