import { View, FlatList } from 'react-native'
import React, {useState} from 'react'
import MapView, {Marker, PROVIDER_GOOGLE} from 'react-native-maps';
import properties from "../../../assets/data/feed"
import CustomMarker from '../../components/CustomMarker';
import PropertyCarouselItem from '../../components/PropertyCarouselItem';

const SearchResultsMap = () => {
    const [selectedPropertyId, setSelectedPropertyId] = useState(null)
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
            {properties.map(property => (<CustomMarker property={property}
                    isSelected={property.id === selectedPropertyId} 
                    onPress={() => setSelectedPropertyId(property.id)}/>))}
        </MapView>
        <View style={{position:"absolute", bottom:-15, left:-10}}>
              <FlatList
                data={properties}
                renderItem={({item}) => <PropertyCarouselItem property={item} />}
                //renderItem={({}) => <PropertyCarouselItem property={item}/>}
                horizontal
              />
            
        </View>
    </View>
  )
}

export default SearchResultsMap