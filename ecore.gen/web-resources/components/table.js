/*
  Table with configurable number of columns to display. 
  Configuration is stored in browser local storage is configKey property is set.
*/
Vue.component('nsd-ecore-doc-table', {
	props: {
    	columns: Array,
    	items: Array,
    	configKey: String
  	},
  	data() {
		let data = {
			totalRows: 1,
        	currentPage: 1,
        	perPage: 20,
        	pageOptions: [5, 10, 15, { value: 100, text: "Show a lot" }],
        	sortBy: '',
        	sortDesc: false,
        	sortDirection: 'asc',
        	filter: null,
        	visibleFields: null,
        	config: {
				items: null,
				columns: ['field', 'visible'],
				visibleFields: null
			}        			
		};
		
		if (this.configKey && window.localStorage) {
			let storedConfigStr = window.localStorage.getItem(this.configKey);
			if (storedConfigStr) {
				try {
					let storedConfig = JSON.parse(storedConfigStr);
					data.visibleFields = storedConfig.visibleFields;
				} catch (error) {
					console.error(error);
				}
			}
			// TODO - sortable and filterable
		}
		if (!data.visibleFields) {
			data.visibleFields = [];
			this.columns.forEach(element => {
				if (element.visible) {
					data.visibleFields.push(element.key);
				}
			});								
		}
		
		return data;
	},
	computed: {
		fields() {
			return this.columns.filter(this.filterColumns);
		},  	
  		filterOn() {
			return this.visibleFields;
		}
	},
	methods: {
		filterColumns(column) {
			return this.visibleFields.indexOf(column.key) !== -1;
		},
		configModal() {
			this.config.items = [];
			this.columns.forEach(element => {
				this.config.items.push({
					key: element.key, 
					field: element.label });
			});
			this.config.visibleFields = this.visibleFields.slice();
			
			this.$root.$emit('bv::show::modal', 'config-modal');
		},
		saveConfig() {
			this.visibleFields = this.config.visibleFields.slice();			
			if (this.configKey && window.localStorage) {
				let storedConfig = {
					visibleFields: this.visibleFields
				};
				window.localStorage.setItem(this.configKey, JSON.stringify(storedConfig));
			}
		},
		onFiltered(filteredItems) {
        // Trigger pagination to update the number of buttons/pages due to filtering
        this.totalRows = filteredItems.length
        this.currentPage = 1
      }				
	}, 
	template: `
		<b-container fluid>		
			<b-row class="my-1">
				<b-col cols="10">
					<b-form-group
			          label="Filter"
			          label-for="filter-input"
			          label-cols-sm="3"
			          label-align-sm="right"
			          label-size="sm"
			          class="mb-0"
			        >
			          <b-input-group size="sm">
			            <b-form-input
			              id="filter-input"
			              v-model="filter"
			              type="search"
			              placeholder="Type to Search"
			            ></b-form-input>
			
			            <b-input-group-append>
			              <b-button :disabled="!filter" @click="filter = ''">Clear</b-button>
			            </b-input-group-append>
			          </b-input-group>
			        </b-form-group>				
				</b-col>
				<b-col cols="2">
					<b-button title="Configuration" size="sm" @click="configModal">
						<i class="fas fa-cog"></i>
        			</b-button>
 					<b-modal id="config-modal" size="lg" scrollable title="Table configuration" @ok="saveConfig">
						<b-table striped hover :items="config.items" :fields="config.columns">
							<template #cell(field)="data">
								<span v-html="data.value"></span>
				      		</template>
							<template #cell(visible)="data">
								<input type="checkbox" :value="data.item.key" v-model="config.visibleFields">
				      		</template>
						</b-table>	    					
  					</b-modal>        			
				</b-col>
			</b-row>
			<b-row>
				<b-col>
					<b-table 
						striped 
						hover
						:items="items"
						:fields="fields"
						@filtered="onFiltered"
						:filter="filter"
      					:filter-included-fields="filterOn">
      					
						<template #head()="data">
							<span v-html="data.label"></span>
			      		</template>
						<template #cell()="data">
							<span v-html="data.value"></span>
			      		</template>
					</b-table>
				</b-row>
			</b-col>
		</b-container>
	`
});