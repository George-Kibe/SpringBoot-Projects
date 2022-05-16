import { View, Text } from 'react-native'
import React from 'react'
import MapView, {Marker} from 'react-native-maps';


const SearchResultsMap = () => {
  return (
    <View style={{width:"100%", height:"100%"}}>
      <MapView style={{width:"100%", height:"100%"}}
        initialRegion={{
        latitude: 28.32,
        longitude: -16.532,
        latitudeDelta: 0.8,
        longitudeDelta: 0.9,
        }}
    >
        
    </MapView>
    </View>
  )
}

export default SearchResultsMap