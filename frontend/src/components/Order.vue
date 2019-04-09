<template>
  <div>
    <v-container fluid grid-list-md>
      <v-layout row wrap>
        <v-flex md3>
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
              <v-list-tile v-for="item in items" :key="item.title" v-on:click="currentCategory = item.title">
                <v-list-tile-content>
                  <v-list-tile-title>{{ item.title }}</v-list-tile-title>
                </v-list-tile-content>
              </v-list-tile>
            </v-list>
          </v-navigation-drawer>
        </v-flex>

        <v-flex md9>
          <v-card>
            <v-container fluid grid-list-lg>
              <v-layout row wrap>
                <v-flex
                  xs12
                  sm6
                  md4
                  v-for="item in filterCategory"
                  :key="item.title"
                >
                  <v-card color="blue-grey darken-2" class="white--text">
                    <v-card-title primary-title>
                      <div>
                        <div class="headline">{{ item.title }}</div>
                        <h4></h4>
                      </div>
                    </v-card-title>
                    <v-card-actions>
                      <v-btn flat dark>Listen now</v-btn>
                    </v-card-actions>
                  </v-card>
                </v-flex>
              </v-layout>
            </v-container>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
    <v-footer fixed="true" height="80px">
      <v-layout justify-center row wrap>
        <div class="sum">Сумма заказа: {{sum}}</div>
        <v-btn>
          Редактировать
        </v-btn>
        <v-btn>
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
      items: [{ title: "Супы" }, { title: "Рыба" }],
      menuItems: [
        { title: "Суп Д", price: "190", category: "Супы" },
        { title: "Cуп B", price: "210", category: "Супы" },
        { title: "Рыба", price: "340", category: "Рыба" }
      ],
      right: null,
      currentCategory: "Супы",
      sum: 0
    };
  },
  computed: {
    filterCategory: function() {
      let category = this.currentCategory;
      return this.menuItems.filter(function(item) {
        if (item.category === category) {
          return item;
        }
      });
    }
  }
};
</script>

<style>
  .sum{
    text-align: center;
    padding-top: 8px;
    font-size: 24px;
  }
</style>
