// pages/logistics/logistics.js
const app = getApp()
const url = require('../../utils/url.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    orderInfo: {
      orderNo: '748965456479864',
      orderCreateTime: '2018-04-14 17:09:34',
      sendTime: '商品预计在4月16日（周一）前送达'
    },
    logisticsData: [
      {
        content: '您的订单正在派送中',
        time: '2018-04-16 17:09:34'
      },
      {
        content: '您的订单已达到广东省深圳市福田区新洲',
        time: '2018-04-16 17:09:34'
      },
      {
        content: '您的订单正在路上飞奔',
        time: '2018-04-16 17:09:34'
      },
      {
        content: '您的订单已出库',
        time: '2018-04-16 17:09:34'
      },
      {
        content: '您的订单正在出库中',
        time: '2018-04-16 17:09:34'
      },
      {
        content: '您的订单已提交',
        time: '2018-04-16 17:09:34'
      }
    ],
    text:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options.wuliuNum)
    this.setData({
      text: options.wuliuNum
    })
    var that = this
    wx.request({
      url: url.url.getGoodsInfo,
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      data: {
        'token': 'UZIYONGYUANDESHEN',
        'session': app.globalData.session,
        'userId': app.globalData.userId,
        "text":this.data.text
      },
      success: function (res) {
        if (res.data.code == 0) {
          that.setData({
            orderInfo: res.data.orderInfo,
            logisticsData: res.data.logisticsData
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