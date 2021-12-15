<template>
  <div class="hello">
    <h1>ESA Course Web Application Template</h1>
    <table-dress/>
    <table-model/>
    <table-manager/>
  </div>
</template>

<script>
import TableModel from './TableModel.vue'
import TableDress from './TableDress.vue'
import TableManager from './TableManager.vue'
export default {
  components: { TableDress , TableModel, TableManager},
  name: 'HelloWorld',
  data() {
    return {

    }
  }
}
</script>

<style scoped>

</style>
