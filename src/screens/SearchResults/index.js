import { View, Text, FlatList } from 'react-native'
import React, {useState, useEffect} from 'react';
import Property from '../../components/Property';
import {API, graphqlOperation} from "aws-amplify"
import {listProperties} from "../../graphql/queries"

const SearchResultsScreen = () => {
  const [properties, setProperties] = useState([])

  useEffect(() => {  
    const fetchProperties = async () =>{
      try{
        const propertiesResults = await API.graphql(
          graphqlOperation(listProperties)
        )
        setProperties(propertiesResults.data.listProperties.items)
      }catch (error){
        console.log(error)
      }
    }
    fetchProperties();
  }, [])
  return (
    <View>
      <FlatList data={properties}
      renderItem={({item}) => <Property property={item}/>}
      />
    </View>
  )
}

export default SearchResultsScreen