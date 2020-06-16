// pages/NI-SJ/NI-SJ.js
const app = getApp()
const url = require('../../utils/url.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    array: ['北京', '郑州', '济南', '西安', '成都', '重庆'],
    objectArray: [
      {
        id: 1,
        name: '北京'
      },
      {
        id: 2,
        name: '郑州'
      },
      {
        id: 3,
        name: '济南'
      },
      {
        id: 4,
        name: '西安'
      },
      {
        id: 5,
        name: '成都'
      },
      {
        id: 6,
        name: '重庆'
      }
    ],
    index1: 0,
    index2: 0,
    weight: ''
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

  },
  bindPickerChange1: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      index1: e.detail.value
    })
  },
  bindPickerChange2: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      index2: e.detail.value
    })
  },
  setweight: function (e) {
    this.setData({
      weight: e.detail.value
    })
  },
  report: function (e) {
    if (this.data.index1 == this.data.index2 | this.data.weight == '') {
      if (this.data.index1 == this.data.index2) {
        wx.showToast({
          title: '地点重复!',
          icon: 'fail',
          image: '/images/fail.png',
          duration: 2000
        })
      }
      if (this.data.weight == '') {
        wx.showToast({
          title: '请填写重量!',
          icon: 'fail',
          image: '/images/fail.png',
          duration: 2000
        })
      }
    } else {
      wx.request({
         url: url.url.report,
         method: 'POST',
         header: {
           'content-type': 'application/x-www-form-urlencoded'
         },
         data: {
           "token": "UZIYONGYUANDESHEN",
           "startId": this.data.index1+1,
           "endId": this.data.index2+1,
           "session": app.globalData.session,
           "weight": this.data.weight,
           "userId": app.globalData.userId
         },
         success: function (res) {
           //console.log(res.data)
           if (res.data.code == 0) {
             wx.showToast({
               title: '提交成功!',
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
               title: '验证失败！',
               icon: 'fail',
               image: '/images/fail.png',
               duration: 2000
             })
           }
         }
       })
    }
  }
})