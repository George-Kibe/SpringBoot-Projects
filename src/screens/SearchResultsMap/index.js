import { View, Text } from 'react-native'
import React from 'react'
import MapView, {Marker, PROVIDER_GOOGLE} from 'react-native-maps';
import properties from "../../../assets/data/feed"
import CustomMarker from '../../components/CustomMarker';

const SearchResultsMap = () => {
  return (
    <View style={{width:"100%", height:"100%"}}>
      <MapView style={{width:"100%", height:"100%"}}
        initialRegion={{
        latitude: -1.286,
        longitude: 36.817,
        latitudeDelta: 0.8,
        longitudeDelta: 0.8,
        }}
        provider={PROVIDER_GOOGLE}
    >
        {properties.map(property => (<CustomMarker property={property} />))}
    </MapView>
    </View>
  )
}

export default SearchResultsMap