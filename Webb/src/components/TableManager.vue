<template>
  <div>
    <v-row>
      <v-col>
        <h1> Manager table</h1>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-btn @click="addNewRowStart">Add new manager</v-btn>
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
            <td>{{ item.fullName }}</td>
            <td>{{ item.costOfServices }}</td>
            <td>{{ item.experience }}</td>
            <td>{{ item.phone }}</td>
            <td>{{ item.email }}</td>
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
// Change this import in respect to the technology currently using in the backend 
import { springGetAll, springDeleteItem, springAddNewItem, springUpdateItem } from "../endpoints/table_manager_endpoints";
//import { eeGetAll, eeDeleteItem, eeAddNewItem, eeUpdateItem } from "../endpoints/table_manager_endpoints";
import DialogExample from "./DialogExample.vue";

export default {
  components: { DialogExample },
  name: "TableManager",
  data() {
    return {
      table_header: ["id","fullName", "costOfServices","experience","phone","email"],
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
      // Placeholder
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
      this.newItem = {'fullName': item.fullName, 'costOfServices': item.costOfServices,
        'experience': item.experience,'phone':item.phone,'email':item.email}

      this.nextFunction = this.editRowEnd
      this.showDialog = true;
    },
    editRowEnd(item) {
      // In case of javaEE 
      // addNewItem(item)
      // In case of Srping 
      const body = {'fullName': item.fullName, 'costOfServices': item.costOfServices,
        'experience': item.experience,'phone':item.phone,'email':item.email}
      // params = {'childId': 1}
      const params = {}
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
      this.newItem = {fullName: '', costOfServices: '', experience:'',phone: '',email:''}
      this.nextFunction = this.addNewRowEnd
      this.showDialog = true;
    },
    addNewRowEnd(item) {
      // In case of javaEE 
      // addNewItem(item)
      // In case of Srping 
      const body = {'fullName': item.fullName, 'costOfServices': item.costOfServices,
        'experience': item.experience,'phone': item.phone,'email':item.email}
      // params = {'childId': 1}
      const params = {}
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
