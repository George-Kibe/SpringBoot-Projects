import { View, Text } from 'react-native'
import React from 'react'
import MapView from 'react-native-maps';


const SearchResultsMap = () => {
  return (
    <View style={{width:"100%", height:"100%"}}>
      <MapView style={{width:"100%", height:"100%"}}
        initialRegion={{
        latitude: 26.278825,
        longitude: -16.5324,
        latitudeDelta: 0.8,
        longitudeDelta: 0.9,

        }}
    />
    </View>
  )
}

export default SearchResultsMap