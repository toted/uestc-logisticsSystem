// pages/register/register.js
const app = getApp()
const url = require('../../utils/url.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userId:'',
    email:'',
    password:'',
    repassword: '',
    verificationCode:'',
    isfinish:true,
  },
  setuserId:function(e){
    this.setData({
      userId:e.detail.value
    })
  },
  setemail: function (e) {
    this.setData({
      email: e.detail.value
    })
  },
  setpassword: function (e) {
    this.setData({
      password: e.detail.value
    })
  },
  setrepassword: function (e) {
    this.setData({
      repassword: e.detail.value
    })
  },
  setverificationCode: function (e) {
    this.setData({
      verificationCode: e.detail.value
    })
  },
  verification:function(e){
    console.log(this.data)
    if (this.data.userId == '' | this.data.email == '' | this.data.password == '' | this.data.repassword == ''){
      wx.showToast({
        title: '请输入完整信息!',
        icon: 'fail',
        image: '/images/fail.png',
        duration: 2000
      })
    }else{
      if(this.data.password!=this.data.repassword){
        wx.showToast({
          title: '两次密码不同!',
          icon: 'fail',
          image: '/images/fail.png',
          duration: 2000
        })
      }else{
        wx.request({
          url: url.url.verification,
          method: 'POST',
          header: {
            'content-type': 'application/x-www-form-urlencoded'
          },
          data: {
            "token": "UZIYONGYUANDESHEN",
            "userId": this.data.userId,
            "email": this.data.email,
            "password": this.data.email,
          },
          success: function (res) {
            //console.log(res.data)
            if (res.data.code == 0) {
              wx.showToast({
                title: '发送成功!',
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
                title: '邮箱已被注册！',
                icon: 'fail',
                image: '/images/fail.png',
                duration: 2000
              })
            }
          }
        })
      }
    }
  },
  register:function(e){
    if (this.data.tel == '' | this.data.email == '' | this.data.vericationCode==''|this.data.password==''|this.data.repassword=='') {
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
      if(this.data.password!=this.data.repassword){
        wx.showToast({
          title: '两次密码不一致!',
          icon: 'fail',
          image: '/images/fail.png',
          duration: 2000
        })
      }else{
      /**
       * 下面是测试跳转
       
      wx.navigateTo({
        url: '../login/login'
      })
      */
      wx.request({
        url: url.url.register,
        method: 'POST',
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        data: {
          "token": "UZIYONGYUANDESHEN",
          "userId": this.data.userId,
          "email": this.data.email,
          "password": this.data.email,
          "verificationCode": this.data.verificationCode
        },
        success: function (res) {
          //console.log(res.data)
          if (res.data.code == 0) {
            wx.showToast({
              title: '注册成功!',
              icon: 'success',
              duration: 2000
            }),
              wx.navigateTo({
                url: '../login/login'
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
              title: '手机号已被注册！',
              icon: 'fail',
              image: '/images/fail.png',
              duration: 2000
            })
          }
          if (res.data.code == 3) {
            wx.showToast({
              title: '验证失败',
              icon: 'fail',
              image: '/images/fail.png',
              duration: 2000
            })
          }
        }
      })
    }
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