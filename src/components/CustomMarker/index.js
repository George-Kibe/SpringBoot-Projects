import { View, Text } from 'react-native'
import React from 'react'
import { Marker } from 'react-native-maps'

const CustomMarker = ({property}) => {
  return (
    <Marker coordinate={property.coordinate} key={property.id}>
        <View style={{backgroundColor:"white",
                    padding:5, borderRadius:20,
                    borderColor:"grey",borderWidth:1}}>
            <Text style={{fontWeight:"bold"}}>Ks.{property.totalPrice}</Text>
        </View>
    </Marker>
  )
}

export default CustomMarker