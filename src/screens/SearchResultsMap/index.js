import { View, FlatList, useWindowDimensions } from 'react-native'
import React, {useState} from 'react'
import MapView, {Marker, PROVIDER_GOOGLE} from 'react-native-maps';
import properties from "../../../assets/data/feed"
import CustomMarker from '../../components/CustomMarker';
import PropertyCarouselItem from '../../components/PropertyCarouselItem';

const SearchResultsMap = () => {
    const [selectedPropertyId, setSelectedPropertyId] = useState(null)
    const { height, width } = useWindowDimensions();
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
        <View style={{position:"absolute", bottom:55, left:0}}>
              <FlatList
                data={properties}
                renderItem={({item}) => <PropertyCarouselItem property={item} />}
                horizontal
                showsHorizontalScrollIndicator={false}
                snapToInterval={width-60}
                snapToAlignment={"center"}
                decelerationRate={"fast"}
              />
            
        </View>
    </View>
  )
}

export default SearchResultsMap