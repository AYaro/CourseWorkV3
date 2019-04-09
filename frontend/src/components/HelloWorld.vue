<template>
  <div class="form">
  <div class="start">
    <h1>{{ msg }}</h1>
    <div class="order">
      <router-link to="/order">Заказать</router-link>
    </div>
    <div class="login">
      <router-link to="/login">Авторизоваться</router-link>
    </div>
  </div>
  </div>
</template>

<script>
export default {
  name: "HelloWorld",
  props: {
    msg: String
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1{
    margin-top: 20px;
  }
  .form{
    padding: 50px;
  }
  .start{
    margin: auto;
    min-width: 300px;
    width: 20%;
    text-align: center;
    border: 1px solid black;
    border-radius: 16px;
  }
  .order, .login{
    padding: 5px;
    margin: auto;
    margin-top: 20px;
    margin-bottom: 20px;
    font-size: 20px;
    border: 1px solid black;
    border-radius: 6px;
    width: 180px;
    background: aquamarine;
  }
  .login{
    margin-bottom: 40px;
  }
a {
  color: black;
  text-decoration: none;
}
</style>
