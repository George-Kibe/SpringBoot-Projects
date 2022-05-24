import React, {useState, useEffect} from 'react'
import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs';
import SearchResults from "../screens/SearchResults"
import SearchResultsMap from '../screens/SearchResultsMap';
import { useRoute } from '@react-navigation/native';
import {API, graphqlOperation} from "aws-amplify"
import {listProperties} from "../graphql/queries"

const Tab = createMaterialTopTabNavigator();

const SearchResultsTabNavigator = () => {
  const route = useRoute()
  const [properties, setProperties] = useState([])
  const {users, viewport} = route.params;

  useEffect(() => {  
    const fetchProperties = async () =>{
      try{
        const propertiesResults = await API.graphql(
          graphqlOperation(listProperties,{
            filter:{
              and:{
                maxUsers:{ge:users},
                latitude: {
                  between: [viewport.southwest.lat, viewport.northeast.lat], 
                },
                longitude: {
                  between: [viewport.southwest.lng, viewport.northeast.lng],
                }
              }
              
            }
          })
        )
        setProperties(propertiesResults.data.listProperties.items)
      }catch (error){
        console.log(error)
      }
    }
    fetchProperties();
  }, [])


  return (
    <Tab.Navigator screenOptions={{
        "tabBarActiveTintColor": "#f15454",
        "tabBarIndicatorStyle": {
          "backgroundColor": "#f15454"
        }
      }}>
        <Tab.Screen name={"List View"}>
          {() =>(
            <SearchResults properties={properties}/>
          )}
        </Tab.Screen>
        <Tab.Screen name={"Map View"}>
          {() =>(
            <SearchResultsMap properties={properties}/>
          )}
        </Tab.Screen>        
    </Tab.Navigator>
  )
}

export default SearchResultsTabNavigator