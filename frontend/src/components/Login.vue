<template>
  <div class="form">
    <div class="login">
      <form id="signin" @submit.prevent="processSignIn">
        <h1>Авторизация</h1>
        <h3>Введите логин</h3>
        <input type="text" v-model="username" placeholder="логин" />
        <p>Введённое сообщение: {{ username }}</p>
        <h3>Введите пароль</h3>
        <input type="password" v-model="password" placeholder="пароль" />
        <p>Введённое сообщение: {{ password }}</p>
        <button type="submit">Войти</button>
      </form>
      <div v-bind="error" id="error" class="error-box" style="display: none;"></div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Login",
  data: function() {
    return {
      username: "",
      password: "",
      error: false
    };
  },
  methods: {
    processSignIn: function() {
      axios
        .post("http://localhost:9090/doLogin",
        ("username=" + this.username + "&password=" + this.password)
        )
        .then(response => {})
        .catch(e => {
          this.errors.push(e);
          element = this.$e.;
        });
    },

    processRegistration: function() {
      "#error".slideUp();
    },
    initRegistration: function() {
      "#registration".slideToggle();
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
