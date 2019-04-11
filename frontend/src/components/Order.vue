<template>
  <div>
    <v-container fluid grid-list-md>
      <v-layout row wrap v-if="!showDetails && !showConfirmation && !showConfirmed">
        <v-flex md3 xs3>
          <v-navigation-drawer permanent>
            <v-toolbar flat>
              <v-list>
                <v-list-tile>
                  <v-list-tile-title class="title">
                    Меню
                  </v-list-tile-title>
                </v-list-tile>
              </v-list>
            </v-toolbar>

            <v-divider></v-divider>

            <v-list dense class="pt-0">
              <v-list-tile
                v-for="item in items"
                :key="item"
                v-on:click="currentCategory = item"
              >
                <v-list-tile-content>
                  <v-list-tile-title>{{ item }}</v-list-tile-title>
                </v-list-tile-content>
              </v-list-tile>
            </v-list>
          </v-navigation-drawer>
        </v-flex>

        <v-flex md9 xs9>
          <v-card>
            <v-container fluid grid-list-lg>
              <v-layout row wrap>
                <v-flex
                  xs12
                  sm6
                  md4
                  v-for="dish in filterCategory"
                  :key="dish.id"
                >
                  <v-card
                    v-on:click="onItemClicked(dish)"
                  >
                    <v-card-title primary-title>
                      <div class="text-md-center img" style="text-align: center">
                        <img width="230" height="180"
                          :src="'data:image/png;base64, ' + dish.image"
                        ></img>
                        <div class="headline">{{ dish.name }}</div>
                        <div>Стоимость: {{ dish.price }}</div>
                      </div>
                    </v-card-title>
                  </v-card>
                </v-flex>
              </v-layout>
            </v-container>
          </v-card>
        </v-flex>
      </v-layout>
      <v-layout justify-center row wrap v-if="showDetails && !showConfirmation">
        <v-flex md6 xs12>
          <v-card v-if="!showComment">
            <v-card-title>Редактирование</v-card-title>
            <v-container fluid list-lg>
              <v-layout row wrap>
                <v-flex md12 xs12>
                  <v-card>
                    <v-layout row wrap>
                      <v-flex md4>
                        <v-card-title>Блюдо</v-card-title>
                      </v-flex>
                      <v-flex md4>
                        <v-card-text>
                          Количество
                        </v-card-text>
                      </v-flex>
                      <v-flex md3>
                        <v-card-text>
                          Стоимость
                        </v-card-text>
                      </v-flex>
                    </v-layout>
                  </v-card>
                  <v-card v-for="dish in chosenItems" :key="dish.id">
                    <v-layout row wrap>
                      <v-flex md4>
                        <v-card-title>
                          <div class="headline">{{ dish.dish.name }}</div>
                        </v-card-title>
                      </v-flex>
                      <v-flex md4>
                        <v-card-text>
                          {{ dish.quantity }}
                          <v-icon
                            @click="
                              {
                                dish.quantity++;
                                var s = 0;
                                for (var i = 0; i < chosenItems.length; i++) {
                                  s =
                                    s +
                                    chosenItems[i].dish.price *
                                      chosenItems[i].quantity;
                                }
                                sum = s;
                              }
                            "
                            >add</v-icon
                          >
                          <v-icon
                            @click="
                              {
                                if (dish.quantity > 1) {
                                  dish.quantity--;
                                  var s = 0;
                                  for (var i = 0; i < chosenItems.length; i++) {
                                    s =
                                      s +
                                      chosenItems[i].dish.price *
                                        chosenItems[i].quantity;
                                  }
                                  sum = s;
                                } else {
                                  deleteChosenItem(dish);
                                }
                              }
                            "
                            >remove</v-icon
                          >
                        </v-card-text>
                      </v-flex>
                      <v-flex md3>
                        <v-card-text>
                          {{ dish.dish.price * dish.quantity }}
                        </v-card-text>
                      </v-flex>
                      <v-flex md1>
                        <v-icon @click="deleteChosenItem(dish)">delete</v-icon>
                      </v-flex>
                    </v-layout>
                  </v-card>
                </v-flex>
                <v-flex md6 class="text-md-center">
                  <v-btn @click="showComment = !showComment">Комментарий</v-btn>
                </v-flex>
                <v-flex md6 class="text-md-center">
                  <v-btn @click="showDetails = !showDetails">Назад</v-btn>
                </v-flex>
              </v-layout>
            </v-container>
          </v-card>
          <v-card v-if="showComment">
            <v-card-title>Комментарий</v-card-title>
            <v-textarea
              solo
              name="input-7-4"
              label="Комментарий к заказу"
              v-model="comment"
            ></v-textarea>
            <v-btn @click="showComment = !showComment">Назад</v-btn>
          </v-card>
        </v-flex>
      </v-layout>
      <v-layout justify-center row wrap v-if="showConfirmation && !showConfirmed">
        <v-flex md6 xs12>
          <v-card>
            <v-card-title>Подтверждение</v-card-title>
            <v-container fluid list-lg>
              <v-layout row wrap>
                <v-flex md12 xs12>
                  <v-card>
                    <v-layout row wrap>
                      <v-flex md4>
                        <v-card-title>Блюдо</v-card-title>
                      </v-flex>
                      <v-flex md4>
                        <v-card-text>
                          Количество
                        </v-card-text>
                      </v-flex>
                      <v-flex md3>
                        <v-card-text>
                          Стоимость
                        </v-card-text>
                      </v-flex>
                    </v-layout>
                  </v-card>
                  <v-card v-for="dish in chosenItems" :key="dish.id">
                    <v-layout row wrap>
                      <v-flex md4>
                        <v-card-title>
                          <div class="headline">{{ dish.dish.name }}</div>
                        </v-card-title>
                      </v-flex>
                      <v-flex md4>
                        <v-card-text>
                          {{ dish.quantity }}
                        </v-card-text>
                      </v-flex>
                      <v-flex md3>
                        <v-card-text>
                          {{ dish.dish.price * dish.quantity }}
                        </v-card-text>
                      </v-flex>
                    </v-layout>
                  </v-card>
                </v-flex>
                <v-flex md12 class="text-md-center">
                  <h2>Сумма заказа: {{ sum }}</h2>
                </v-flex>
                <v-flex md12 class="text-md-center">
                  <v-text-field
                    v-model="table"
                    type="number"
                    :rules="numberRules"
                    :label="errorName"
                  >
                    label="Номер столика" ></v-text-field
                  >
                </v-flex>
                <v-flex md6 class="text-md-center">
                  <v-btn @click="showConfirmation = !showConfirmation"
                    >Назад</v-btn
                  >
                </v-flex>
                <v-flex md6 class="text-md-center">
                  <v-btn @click="confirmOrder()">Подтвердить</v-btn>
                </v-flex>
              </v-layout>
            </v-container>
          </v-card>
          <v-card v-if="showComment">
            <v-card-title>Комментарий</v-card-title>
            <v-textarea
              solo
              name="input-7-4"
              label="Комментарий к заказу"
              v-model="comment"
            ></v-textarea>
            <v-btn @click="showComment = !showComment">Назад</v-btn>
          </v-card>
        </v-flex>
      </v-layout>
      <v-layout justify-center row wrap v-if="showConfirmed">
        <v-flex md6 xs12>
          <v-card>
            <v-card-title>Заказ принят на обработку</v-card-title>
            <v-card-title>Ваш заказ №{{id}}</v-card-title>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
    <v-footer fixed height="80px" v-if="!showConfirmed">
      <v-layout justify-center row wrap>
        <div class="sum">Сумма заказа: {{ this.sum }}</div>
        <v-btn @click="showDetails = !showDetails">
          Редактировать
        </v-btn>
        <v-btn @click="showConfirmation = !showConfirmation">
          подтвердить
        </v-btn>
      </v-layout>
    </v-footer>
  </div>
</template>

<script>
import axios from "axios";

import Vue from "vue";
import Vuesax from "vuesax";

import "vuesax/dist/vuesax.css"; //Vuesax styles
Vue.use(Vuesax);

export default {
  name: "Order",
  data() {
    return {
      items: ["Супы", "Рыба"],
      menuItems: [
        { id: 1, title: "Суп Д", price: 190, category: "Супы" },
        { id: 2, title: "Cуп B", price: 210, category: "Супы" },
        { id: 3, title: "Суп М", price: 122, category: "Супы" },
        { id: 4, title: "Cуп С", price: 211, category: "Супы" },
        { id: 5, title: "Рыба", price: 340, category: "Рыба" }
      ],
      right: null,
      id: 0,
      table: 0,
      currentCategory: "Супы",
      chosenItems: [],
      comment: "",
      sum: 0,
      showDetails: false,
      showComment: false,
      showConfirmed: false,
      showConfirmation: false,
      maxLength: 45,
      minLength: 1,
      errorName: "",
      numberRules: [
        v => !!v || "Input is required",
        v =>
          v < this.maxLength ||
          `${this.errorName} must be less than ${this.maxLength}`,
        v =>
          v < this.minLength ||
          `${this.errorName} must be greater than ${this.minLength}`
      ]
    };
  },
  created: function() {
    axios
      .get("/api/dish/categories")
      .then(response =>
        (this.items = response.data))
      .catch(error => console.log(error));
    axios
      .get("/api/dish")
      .then(response => {(this.menuItems = response.data); console.log(this.menuItems)})
      .catch(error => console.log(error));
  },
  computed: {
    filterCategory: function() {
      let category = this.currentCategory;
      return this.menuItems.filter(function(dish) {
        if (dish.category === category) {
          return dish;
        }
      });
    }
  },
  methods: {
    deleteChosenItem(dish) {
      var n = this.chosenItems.findIndex(function(chosenItem) {
        return (
          chosenItem.dish.id === dish.dish.id
        );
      });
      this.chosenItems.splice(n, 1);
    },
    onItemClicked(dish) {
      var n = this.chosenItems.findIndex(function(chosenItem) {
        return (
          chosenItem.dish.id === dish.id
        );
      });
      if (n > -1) {
        this.chosenItems[n].quantity = this.chosenItems[n].quantity + 1;
      } else {
        this.chosenItems.push({ dish, quantity: 1 });
      }
    },
    confirmOrder() {
      if (
        this.table >= 1 &&
        this.table <= 45 &&
        this.table.toString.length <= 2
      ) {
        this.showConfirmation = !this.showConfirmation;
        let axiosConfig = {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8',
          }
        };
        axios.put("/api/order?"+ "table_number=" + this.table +"&comment" + this.comment, this.chosenItems, axiosConfig).then(response =>
                (this.id = response.data)).catch(e => {
          this.errors.push(e);
        });
        this.showConfirmation = false;
        this.showConfirmed = true;
      }
    }
  },
  watch: {
    chosenItems: function() {
      var s = 0;
      for (var i = 0; i < this.chosenItems.length; i++) {
        s = s + this.chosenItems[i].dish.price * this.chosenItems[i].quantity;
      }
      this.sum = s;
    }
  }
};
</script>

<style>
.sum {
  text-align: center;
  padding-top: 8px;
  font-size: 24px;
}
.v-card--reveal {
    align-items: center;
  bottom: 0;
  justify-content: center;
  opacity: 0.5;
  position: absolute;
  width: 100%;
}
  .img{
    width: 100%;
  }
</style>
