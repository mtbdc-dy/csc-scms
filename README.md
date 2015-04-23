# csc-scms
China Scholarship Counil - Study in China Management System - Service

基金委来华留学生项目-服务端

基于RESTful实现API



## 遇到的问题(╯▔皿▔)╯

由于项目是部署在Weblogic 11g（10.3.6）上，其中内置的JavaEE5，然后此项目架构使用的Hibernate 4.3.8无论是JPA方式还是Native Hibernate方式都无法很好的与其
兼容。前者在应用启动时会遇到
```
javax.persistence.spi.PersistenceUnitInfo.getValidationMethod()
```
为空，而后者又会因为
```
javax.persistence.OneToOne.orphanRemoval()
```
为空而无法启动。
## 原因及解决方法
纠结了几天之后，中间走了不少弯路，重新梳理了一下思路。
根本原因就是Weblogic 11g会默认优先加载其内置API，所以只让它更够在启动时加载我们需要的API，
并且优先级还要是最高。一番查阅之后发现了`PRE_CLASSPATH`这个属性
，并解决了这个问题。

- 将用到的`hibernate-jpa-2.1-api-1.0.0.Final.jar`复制到Weblogic11g的
Modules文件夹下一般为`C:\Oracle\Middleware\modules`。

- 在`%WL_HOME%`中找到`common\bin\commEnv.cmd`，如果为了不影响其他项目
也可以修改对应`Domain`中的`startWeblogic.cmd`，在里面加入

```
@REM Hack JPA begin
echo Hack JPA begin
set wls_modules=C:\oracle\Middleware\modules
set PRE_CLASSPATH=%wls_modules%\hibernate-jpa-2.1-api-1.0.0.Final.jar
echo PRE_CLASSPATH=%PRE_CLASSPATH%
echo Hack JPA End
@REM Hack JPA END
```

- 重启`Weblogic`，搞定，收工。

最后，希望还在继续奋斗在Weblogic11g上各种坑的同胞们尽早脱离苦海o(*￣▽￣*)ブ

# Restful APIs

后端系统中对外发布复合 Restful API 设计的资源

## Student

学生资源

### 资源URI `/student`

HTTP请求 | 可用性 | 期望返回值 | 说明
------- | ----- | -------- | ---
GET | 支持 | JSON 数组 | API服务器返回对应的Student列表
POST | 不支持 | - | -
PUT | 不支持 | - | -
DELETE | 不支持 | - | -

### 资源格式

Student JSON Object Example

```
{
  "studentId": 1,
  "cscId": "CSC11000001",
  "sex": null,
  "birthday": null,
  "passportName": null,
  "certificateNO": null,
  "studentType": null,
  "country": "美国",
  "planLeaveDate": 61414992000000,
  "registerState": null,
  ...
}
```

Student JSON Array Example

```
[{
  "studentId": 1,
  "cscId": "CSC11000001",
  ...
},{
  "studentId": 1,
  "cscId": "CSC11000001",
  ...
},...]
```
