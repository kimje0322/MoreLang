<template>
  <v-card color="transparent" flat class="pa-1 mt-6">
    <v-card-title>
      풀이목록
      <v-spacer></v-spacer>
      <v-text-field
        append-icon="mdi-magnify"
        label="검색"
        single-line
        hide-details
        v-model="search"
      ></v-text-field>
    </v-card-title>
    <v-divider></v-divider>
    <hr />
    <v-data-table
      v-model="selected"
      :headers="headers"
      :items="solutions"
      :search="search"
      no-data-text="😢 데이터가 없습니다 😢"
      item-key="scoring"
      :items-per-page="8"
      class="elevation-1"
      cols="1"
      :footer-props="{
        itemsPerPageOptions: [8],
        showFirstLastPage: true,
        firstIcon: 'mdi-arrow-collapse-left',
        lastIcon: 'mdi-arrow-collapse-right',
        prevIcon: 'mdi-minus',
        nextIcon: 'mdi-plus'
      }"
    >
      <template v-slot:[`item.title`]="{ item }">
        <v-flex
          style="cursor:pointer; width : 100%;"
          class="text-truncate"
          @click="$router.push({ name: 'LogDetail', params: { id: item.scoring } })"
        >
          {{ item.title }}
        </v-flex>
      </template>
      <template v-slot:[`item.problemid`]="{ item }">
        <span
          style="cursor:pointer"
          @click="$router.push({ name: 'ProblemDetail', params: { id: item.problemid } })"
          >{{ item.problemid }}</span
        >
      </template>
      <template v-slot:[`item.problemname`]="{ item }">
        <span
          style="cursor:pointer"
          @click="$router.push({ name: 'ProblemDetail', params: { id: item.problemid } })"
          >{{ item.problemname }}</span
        >
      </template>
      <template v-slot:[`item.keyword`]="{ item }">
        <span :title="item.keyword">{{ item.keyword2 }}</span>
      </template>
      <v-alert slot="no-results" :value="true">
        검색어 : "{{ search }}" 에 관한 글이 없습니다.
      </v-alert>
    </v-data-table>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
        items: null,
    };
  },
  mounted() {
    axios
      .get(
        `https://morelang.gq/api/user/pay/my-pointlogs?direction=ASC&page=0&size=10`
      )
      .then(res => {
        console.log(res.data.content);
        this.items = res.data.content;
      });
  },
  methods: {}
};
import axios from "axios";
</script>
    
<style>
</style>