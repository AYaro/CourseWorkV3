<template>
  <header class="header">
    <h1>Restaurant X</h1>
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link> |
      <router-link to="/contacts">Contacts</router-link>
    </div>
  </header>
</template>

<script>
export default {
  name: "Header"
};
</script>

<style scoped>
.header {
  background: #ccc;
  color: #333;
  text-align: center;
  padding: 6px;
  padding-top: 12px;
}
.header a {
  color: #333;
  padding-right: 5px;
  text-decoration: none;
}
#nav {
  text-align: end;
}
</style>
