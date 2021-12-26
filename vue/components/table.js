Vue.component('nsd-table', {
  props: {
    columns: Array,
    items: Array
  },
  data: function () {
    return {
      count: 0
    }
  },
computed: {
	fields() {
		// TODO - filter using configuration.
		return this.columns;
	}, 
	rows() {
		return this.items;
  	}  	
},
template: `
	<b-container>
		<b-table striped hover :items="rows" :fields="fields">
			<template #cell()="data">
				<span v-if="data.value.html" v-html="data.value.value"></span>
				<span v-else> {{ data.value.value }}</span>
      		</template>
		</b-table>
	</b-container>
`
});