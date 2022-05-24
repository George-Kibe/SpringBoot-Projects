import { View, Text } from 'react-native'
import React, {useState, useEffect} from 'react'
import DetailedProperty from "../../components/DetailedProperty"
import properties from "../../../assets/data/feed"
import { useRoute } from '@react-navigation/native'
import {API, graphqlOperation} from "aws-amplify"
import {getProperty} from "../../graphql/queries"


const propertytest = properties[2]
const PropertyScreen = () => {
  const [property, setProperty] = useState([])
  const route = useRoute()
  //const property = properties.find(property => property.id === route.params.propertyId)
  console.warn(route.params.propertyId)
  useEffect(() => {  
    const fetchSpecificProperty = async () =>{
      try{
        const propertyResult = await API.graphql(
          graphqlOperation(getProperty, {id:route.params.propertyId})
        )
        //console.log(propertyResult)
        setProperty(propertyResult.data.getProperty)
      }catch (error){
        console.log(error)
      }
    }
    fetchSpecificProperty();
  }, [])

  return (
    <View style={{ backgroundColor: "white" }}>
      <DetailedProperty property={property? property : propertytest} />
    </View>
  )
}

export default PropertyScreen