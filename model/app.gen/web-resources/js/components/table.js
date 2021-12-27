/*
  Table with configurable number of columns to display. 
  Configuration is stored in browser local storage is configKey property is set.
*/
Vue.component('nsd-table', {
	props: {
    	columns: Array,
    	items: Array,
    	configKey: String
  	},
	computed: {
		fields() {
			return this.columns.filter(this.filterColumns);
		}, 
		rows() {
			return this.items.filter(this.filterItems);
  		}  	
	},
	methods: {
		filterColumns(column) {
			// TODO - from local storage.
			
			return column.visible;
		},
		filterItems(item) {
			return true;
		}
	}, 
	template: `
		<b-container fluid>		
			<b-table striped hover :items="rows" :fields="fields">
				<template #head()="data">
					<span v-html="data.label"></span>
	      		</template>
				<template #cell()="data">
					<span v-html="data.value"></span>
	      		</template>
			</b-table>
		</b-container>
	`
});