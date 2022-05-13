import React from 'react'
import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs';
import SearchResults from "../screens/SearchResults"

const Tab = createMaterialTopTabNavigator();

const SearchResultsTabNavigator = () => {
  return (
    <Tab.Navigator screenOptions={{
        "tabBarActiveTintColor": "#f15454",
        "tabBarIndicatorStyle": {
          "backgroundColor": "#f15454"
        }
      }}>
        <Tab.Screen  
            name={"List View"} component={SearchResults}/>
        <Tab.Screen name={"Map View"} component={SearchResults}/>        
    </Tab.Navigator>
  )
}

export default SearchResultsTabNavigator