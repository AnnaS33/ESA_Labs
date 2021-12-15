<template>
  <v-dialog v-model="dialog" max-width="600px" @click:outside="closeDialog">
    <v-card>
      <v-card-title>
        {{ title }}
      </v-card-title>
      <v-card-text>
        <v-container>
          <v-row>
            <v-col cols="12" v-for="(propertyValue, propertyKey) in item" v-bind:key="propertyKey">
              <v-text-field :label="propertyKey" v-model="item[propertyKey]"/>
            </v-col>
            <v-col>
              <v-btn color='success' @click="next">Next</v-btn>
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: "DialogExample",
  props: ["item", "dialog", "title"],
  data() {
    return {};
  },
  methods: {
    closeDialog() {
      this.$emit('close')
    },
    next() {
      this.$emit('next', this.item)
    }
  }
};
</script>

<style scoped>
</style>
