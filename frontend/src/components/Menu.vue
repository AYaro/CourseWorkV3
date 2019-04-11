<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <v-container fluid grid-list-md>
    <v-layout row wrap>
      <v-flex md3 xs3>
        <v-navigation-drawer mobile-break-point="600px">
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
              :key="item.title"
              v-on:click="router.go(item.link)"
            >
              <v-list-tile-content>
                <v-list-tile-title>{{ item.title }}</v-list-tile-title>
              </v-list-tile-content>
            </v-list-tile>
          </v-list>
        </v-navigation-drawer>
      </v-flex>

      <v-flex md9 xs9>
        <v-layout row wrap>
          <v-flex md12>
            <v-card>
              <v-layout row wrap>
                <v-flex md2>Название:</v-flex>
                <v-flex md2>Категория:</v-flex>
                <v-flex md1>Цена:</v-flex>
                <v-flex md1>Вес:</v-flex>
                <v-flex md3>Описание:</v-flex>
                <v-flex md1>В меню:</v-flex>
                <v-flex md2>Категория:</v-flex>
                <v-flex md2
                  ><v-text-field v-model="name"></v-text-field
                ></v-flex>
                <v-flex md2
                  ><v-text-field v-model="category"></v-text-field
                ></v-flex>
                <v-flex md1
                  ><v-text-field v-model="price" type="number"></v-text-field
                ></v-flex>
                <v-flex md1
                  ><v-text-field v-model="weight" type="number"></v-text-field
                ></v-flex>
                <v-flex md3
                  ><v-text-field v-model="description"></v-text-field
                ></v-flex>
                <v-flex md1><v-checkbox v-model="inMenu"></v-checkbox></v-flex>
                <v-flex md2
                  ><picture-input
                    ref="pictureInput"
                    @change="onChange"
                    width="100"
                    height="100"
                    margin="16"
                    accept="image/jpeg,image/png"
                    size="10"
                    buttonClass="btn"
                    :customStrings="{
                      upload: '<h1>Bummer!</h1>',
                      drag: 'add image'
                    }"
                  >
                  </picture-input
                ></v-flex>
                  <v-flex md3>
                      <v-btn @click="addItem">
                            Добавить
                      </v-btn>
                  </v-flex>
                  <v-flex md2 class="del">
                      <h3>Удалить по ид: </h3>
                  </v-flex>
                  <v-flex md2>
                      <v-text-field v-model="deleteId" type="number"></v-text-field
                      >
                  </v-flex>
                  <v-flex md2>
                      <v-btn @click="deleteItem()">Удалить</v-btn>
                  </v-flex>
              </v-layout>
            </v-card>
            <v-data-table
              :headers="headers"
              :items="menuItems"
              class="elevation-1"
            >
              <template slot="headerCell" slot-scope="props">
                <v-tooltip bottom>
                  <template v-slot:activator="{ on }">
                    <span v-on="on">
                      {{ props.header.text }}
                    </span>
                  </template>
                  <span>
                    {{ props.header.text }}
                  </span>
                </v-tooltip>
              </template>
              <template v-slot:items="props">
                <td>{{ props.item.id }}</td>
                <td class="text-xs-right">{{ props.item.name }}</td>
                <td class="text-xs-right">{{ props.item.category }}</td>
                <td class="text-xs-right">{{ props.item.price }}</td>
                <td class="text-xs-right">{{ props.item.weight }}</td>
                <td class="text-xs-right">{{ props.item.description }}</td>
                <td class="text-xs-right">{{ props.item.inMenu }}</td>
              </template>
            </v-data-table>

          </v-flex>
        </v-layout>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import axios from "axios";
import router from 'vue-router'
import FormData from 'form-data'
import PictureInput from "vue-picture-input";

export default {
  name: "Menu.vue",
  data() {
    return {
      menuItems: [],
      items: [{ title: "Меню", link:"/admin/menu" }, { title: "Сотрудники", link:"admin/users" }],
      headers: [
        {
          text: "ID",
          align: "left",
          sortable: false,
          value: "id"
        },
        { text: "Название", value: "name" },
        { text: "Категория", value: "category" },
        { text: "Цена", value: "price" },
        { text: "Вес", value: "weight" },
        { text: "Описание", value: "description" },
        { text: "В меню", value: "inMenu" }
      ],
      name: "",
      category: "",
      price: 0,
      weight: 0,
      description: "",
      inMenu: true,
      image: "",
        deleteId: 0
    };
  },
    methods: {
        onChange (image) {
            console.log('New picture selected!')
            if (image) {
                console.log('Picture loaded.')
                // this.image = this.$refs.pictureInput.file
                // console.log(image)
            } else {
                console.log('FileReader API not supported: use the <form>, Luke!')
            }
        },
        addItem (){
          let datas = new FormData();
          datas.append("image", this.$refs.pictureInput.file);
            let axiosConfig = {
                headers: {
                    'Content-Type': `multipart/form-data`
                }
            };
            axios.put("/api/dish?"+ "name=" + this.name +"&category=" + this.category + "&price=" + this.price + "&weight=" + this.weight + "&description=  " + this.description + "&inMenu=" + this.inMenu, datas, axiosConfig).then(response =>
                (this.id = response.data)).catch(e => {
                this.errors.push(e);
            });
        }
    },
  components: {
    PictureInput,
    router
  },
  beforeCreate() {
    axios
      .get("/api/dish/all")
      .then(response => {
        this.menuItems = response.data;
        console.log(this.menuItems);
      })
      .catch(error => console.log(error));
  }
};
</script>

<style scoped>
</style>
