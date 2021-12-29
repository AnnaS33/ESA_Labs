<template>
  <div>
    <v-row>
      <v-col>
        <h1> Dress table</h1>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-btn @click="addNewRowStart">Add new dress</v-btn>
      </v-col>
    </v-row>
    <v-simple-table>
      <template v-slot:default>
        <thead>
          <tr>
            <th
              class="text-left"
              v-for="item in table_header"
              v-bind:key="item"
            >
              {{ item }}
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in table_data" v-bind:key="item.id">
            <td>{{ item.id }}</td>
            <td>{{ item.color }}</td>
            <td>{{ item.cost }}</td>
            <td>{{ item.numberD }}</td>
            <td>
              <v-btn icon @click="editRowStart(item)">
                <v-icon>mdi-pen</v-icon>
              </v-btn>
              <v-btn icon @click="deleteRow(item.id)">
                <v-icon>mdi-delete</v-icon>
              </v-btn>
            </td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
    
    <dialog-example
      :dialog="showDialog"
      :item="newItem"
      :title="title"
      @close="closeDialog"
      @next="nextFunction"
    />
  </div>
</template>

<script>
//Выбираем текущее или закоменченое в зависимости от того с чем работаем - java ee или spring
import { springGetAll, springDeleteItem, springAddNewItem, springUpdateItem } from "../endpoints/table_dress_endpoints";
//import { eeGetAll, eeDeleteItem, eeAddNewItem, eeUpdateItem } from "../endpoints/table_dress_endpoints";
import DialogExample from "./DialogExample.vue";

export default {
  components: { DialogExample },
  name: "TableDress",
  data() {
    return {
      table_header: ["id", "color","cost","numberD"],
      table_data: [],
      showDialog: false,
      newItem: {username: '', role: '', password: '',},
      editingItemIndex: -1,
      title: '',
    };
  },
  computed: {},
  mounted() {
    this.getAllRows();
  },
  methods: {
    nextFunction() {
    },
    getAllRows() {
      //eeGetAll().then((response) => {
      springGetAll().then((response) => {
          this.table_data = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteRow(itemId) {
      //eeDeleteItem(itemId)
      springDeleteItem(itemId)
        .then(() => {
          this.getAllRows();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    editRowStart(item) {
      this.title = `Modify existing user #${item.id}`
      this.editingItemIndex = item.id
      // Defines fields we need to fill in the dialog
      this.newItem = {'color': item.color, 'cost': item.cost, 'numberD': item.numberD,'modelId': item.model ? item.model.id : ''}

      this.nextFunction = this.editRowEnd
      this.showDialog = true;
    },
    editRowEnd(item) {
      // In case of javaEE
      // addNewItem(item)
      // In case of Srping 
      const body = {'color': item.color, 'cost': item.cost, 'numberD': item.numberD}
      const params = {'modelId':item.modelId}
      //eeUpdateItem(this.editingItemIndex, params, body)
      springUpdateItem(this.editingItemIndex, params, body)
        .then((response) => {
          console.log(response.data);
          this.getAllRows();
        })
        .catch((error) => {
          console.log(error);
        });
      this.editingItemIndex = -1
      this.closeDialog()
    },
    addNewRowStart() {
      this.title = 'Add new dress'
      // Defines fields we need to fill in the dialog
      this.newItem = {color: '', cost: '', numberD:'',modelId: ''}
      this.nextFunction = this.addNewRowEnd
      this.showDialog = true;
    },
    addNewRowEnd(item) {
      // In case of javaEE 
      // addNewItem(item)
      // In case of Srping 
      const body = {'color': item.color, 'cost': item.cost, 'numberD': item.numberD}
      const params = {'modelId': item.modelId}
      //eeAddNewItem(params, body)
      springAddNewItem(params, body)
        .then((response) => {
          console.log(response.data);
          this.getAllRows();
        })
        .catch((error) => {
          console.log(error);
        });

      this.closeDialog()
    },
    closeDialog() {
      this.showDialog = false
    },
  },
};
</script>

<style scoped>
</style>
