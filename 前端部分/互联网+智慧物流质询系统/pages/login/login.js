// pages/login/login.js
const app = getApp()
const url = require('../../utils/url.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userId:'',
    password:'',
    isFinish: true,
  },

  setuserId:function(e){
    this.setData({
      userId:e.detail.value
    })
  },

  setpassword:function(e){
    this.setData({
      password:e.detail.value
    })
  },
  login: function (e) {
    var that=this
    if (this.data.userId == '' | this.data.password == '' ) {
      this.setData({
        isFinish: false
      })
    } else {
      this.setData({
        isFinish: true
      })
    }
    if (this.data.isFinish == false) {
      wx.showToast({
        title: '输入不能为空!',
        icon: 'fail',
        image: '/images/fail.png',
        duration: 2000
      })
    } else {
      /**
       * 下面是测试跳转
          
      wx.navigateTo({
        url:'../myIndex/myIndex'
      })
  */
      wx.request({
        url: url.url.login,
        method: 'POST',
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        data: {
          'token': 'UZIYONGYUANDESHEN',
          'userId': this.data.userId,
          'password': this.data.password,
        },
        success: function (res) {
          //console.log(res.data)
          app.globalData.session = res.data.session//设置session
          if (res.data.code == 0) {
            app.globalData.userId = that.data.userId
            wx.showToast({
              title: '登陆成功!',
              icon: 'success',
              duration: 2000
            }),
              wx.navigateTo({
                url: '../myIndex/myIndex'
              })
          }
          if (res.data.code == 1) {
            wx.showToast({
              title: '账号不存在！',
              icon: 'fail',
              image: '/images/fail.png',
              duration: 2000
            })
          }
          if (res.data.code == 2) {
            wx.showToast({
              title: '密码错误！',
              icon: 'fail',
              image: '/images/fail.png',
              duration: 2000
            })
          }
          if (res.data.code == 3) {
            wx.showToast({
              title: '参数错误',
              icon: 'fail',
              image: '/images/fail.png',
              duration: 2000
            })
          }
        }
      })
    }
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