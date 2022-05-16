import { View, Text } from 'react-native'
import React, {useState} from 'react'
import { Marker } from 'react-native-maps'


const CustomMarker = ({property, isSelected, onPress}) => {
  return (
    <Marker coordinate={property.coordinate} key={property.id}
            onPress={onPress}>
        <View style={{backgroundColor: isSelected? "black" : "white",
                    padding:5, borderRadius:20,
                    borderColor:"grey",borderWidth:1}}>
            <Text style={{fontWeight:"bold",
                          color: isSelected? "white":"black"}}
          >Ks.{property.totalPrice}</Text>
        </View>
    </Marker>
  )
}

export default CustomMarker