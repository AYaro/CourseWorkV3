<template>
  <div id="app">
    <div id="nav">|
      <router-link to="/user">User</router-link>
      <router-link to="/hello">Hello</router-link>
      <router-link to="/test1">T1</router-link>
      <router-link to="/test2">T2</router-link>
    </div>
    <router-view :hellomsg="msg"></router-view>
  </div>
</template>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
