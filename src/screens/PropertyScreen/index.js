import { View, Text } from 'react-native'
import React from 'react'
import DetailedProperty from "../../components/DetailedProperty"
import properties from "../../../assets/data/feed"
import { useRoute } from '@react-navigation/native'

const propertytest = properties[2]
const PropertyScreen = () => {
  const route = useRoute()
  const property = properties.find(property => property.id === route.params.propertyId)

  return (
    <View style={{ backgroundColor: "white" }}>
      <DetailedProperty property={property} />
    </View>
  )
}

export default PropertyScreen