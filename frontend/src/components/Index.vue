<template>
  <div id="site-header">
    <div>
      <h1>Лабораторная работа № 4</h1>
      <h2>Дисциплина "Программирование интернет-приложений"</h2>
      <h2><b>Ярошевич Александр Владимирович, P3217</b></h2>
      <h2>Вариант 64618</h2>
    </div>
    <div id="site-header-logo">
      <img src="img/vt.png" alt="Тут утка" />
    </div>
  </div>

  <div id="site-content-wrapper">
    <section>
      <img
        id="loader"
        src="/img/loader.png"
        alt="Загрузка..."
        style="display:none;"
      />
      <h1>Добро пожаловать!</h1>
      <form id="signin" @submit.prevent="processSignIn">
        <input
          type="text"
          placeholder="Логин"
          v-model="signinLogin"
          required
          minlength="3"
          maxlength="20"
        />
        <input
          type="password"
          placeholder="Пароль"
          v-model="signinPassword"
          required
          minlength="5"
        />
        <button type="submit">Войти</button>
      </form>
      <p>
        <a @click="initRegistration">Нет аккаунта?</a>
      </p>
      <form
        id="registration"
        @submit.prevent="processRegistration"
        style="display: none;"
      >
        <input
          type="text"
          id="registration-input-login"
          placeholder="Логин"
          v-model="signupLogin"
          required
          minlength="3"
          maxlength="20"
        />

        <input
          type="password"
          id="registration-input-password"
          placeholder="Пароль"
          v-model="signupPassword"
          required
          minlength="5"
        />

        <input
          type="email"
          id="registration-input-email"
          placeholder="E-mail"
          v-model="signupEmail"
          required
        />
        <button type="submit">Зарегистрироваться</button>
      </form>
      <div id="error" class="error-box" style="display: none;"></div>
    </section>
  </div>
</template>

<script>
import Index from "./Index";
const LOADER_FADE_DURATION_MS = 150;

function handleInputValidity(inputid, val, restrictedval, errmsg) {
  if (val === restrictedval) {
    document.getElementById(inputid).setCustomValidity(errmsg);
  } else {
    document.getElementById(inputid).setCustomValidity("");
  }
}

function handleSignupLoginInputValidity() {
  handleInputValidity(
    "registration-input-login",
    Index.signupLogin,
    Index.usedSignupLogin,
    "Пользователь с таким логином уже существует!"
  );
}

function handleSignupEmailInputValidity() {
  handleInputValidity(
    "registration-input-email",
    Index.signupEmail,
    Index.usedSignupEmail,
    "Пользователь с таким e-mail уже существует!"
  );
}

export default {
  name: "Index",
  el: "#site-content-wrapper",
  data() {
    return {
      signinLogin: null,
      signinPassword: null,
      signupLogin: null,
      signupPassword: null,
      signupEmail: null,
      usedSignupLogin: null,
      usedSignupEmail: null
    };
  },
  methods: {
    processSignIn: function() {
      $("#loader").fadeIn(LOADER_FADE_DURATION_MS);

      $.ajax("/api/v1/auth/login", {
        method: "POST",
        data: {
          username: this.signinLogin,
          password: this.signinPassword
        }
      })
        .fail(function(data) {
          $("#loader").fadeOut(LOADER_FADE_DURATION_MS);

          let r = data.responseJSON;

          if (r.status === 400 && r.message.startsWith("UT001")) {
            window.location = "/check.html";
          } else if (r.status === 400 && r.message.startsWith("UT002")) {
            $("#error")
              .html("Неправильное имя пользователя или пароль.")
              .slideDown();
          } else {
            $("#error")
              .html("Извините, произошла внутренняя ошибка.")
              .slideDown();
          }
        })
        .done(function(data) {
          window.location = "/check.html";
        });
    },

    processRegistration: function() {
      $("#error").slideUp();
      $("#loader").fadeIn(LOADER_FADE_DURATION_MS);

      $.ajax("/api/v1/auth/register", {
        method: "POST",
        data: {
          username: this.signupLogin,
          password: this.signupPassword,
          email: this.signupEmail
        }
      })
        .fail(function(data) {
          $("#loader").fadeOut(LOADER_FADE_DURATION_MS);

          let r = data.responseJSON;

          if (r.status === 400 && r.message.startsWith("UR001")) {
            vm.usedSignupLogin = vm.signupLogin;
            handleSignupLoginInputValidity();
            $("#registration button").click();
          } else if (r.status === 400 && r.message.startsWith("UR002")) {
            vm.usedSignupEmail = vm.signupEmail;
            handleSignupEmailInputValidity();
            $("#registration button").click();
          } else {
            $("#error")
              .html("Извините, произошла внутренняя ошибка.")
              .slideDown();
          }
        })
        .done(function(data) {
          window.location = "/check.html";
        });
    },
    initRegistration: function() {
      $("#registration").slideToggle();
    }
  },
  watch: {
    signupLogin: handleSignupLoginInputValidity,
    signupEmail: handleSignupEmailInputValidity
  }
};
</script>
