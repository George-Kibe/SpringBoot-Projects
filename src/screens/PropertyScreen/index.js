import { View, Text } from 'react-native'
import React from 'react'
import DetailedProperty from "../../components/DetailedProperty"
import properties from "../../../assets/data/feed"

const property = properties[2]
const PropertyScreen = () => {
  return (
    <View style={{backgroundColor:"white"}}>
      <DetailedProperty property={property} />
    </View>
  )
}

export default PropertyScreen