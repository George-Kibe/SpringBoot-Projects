import { View, FlatList, useWindowDimensions } from 'react-native'
import React, {useState, useEffect, useRef} from 'react'
import MapView, {PROVIDER_GOOGLE} from 'react-native-maps';
import properties from "../../../assets/data/feed"
import CustomMarker from '../../components/CustomMarker';
import PropertyCarouselItem from '../../components/PropertyCarouselItem';

const SearchResultsMap = () => {
    const [selectedPropertyId, setSelectedPropertyId] = useState(null)
    const { height, width } = useWindowDimensions();
    const flatList = useRef()
    const map = useRef()

    const viewConfig = useRef({itemVisiblePercentThreshold: 70})
    const onViewChanged = useRef(({viewableItems}) => {
      if (viewableItems.length > 0) {
        const selectedProperty = viewableItems[0].item;
        setSelectedPropertyId(selectedProperty.id)
      }
    })

    useEffect(() => {
      if(!selectedPropertyId || !flatList){
        return
      }
      const index = properties.findIndex(property => property.id === selectedPropertyId)
      flatList.current.scrollToIndex({index})

      const selectedProperty = properties[index];
      const region = {
        latitude: selectedProperty.coordinate.latitude,
        longitude: selectedProperty.coordinate.longitude,
        latitudeDelta: 0.8,
        longitudeDelta: 0.8,
      }
      map.current.animateToRegion(region);
    }, [selectedPropertyId])
    
    return (
    <View style={{width:"100%", height:"100%"}}>
      <MapView style={{width:"100%", height:"100%"}}
        initialRegion={{
        latitude: -1.286,
        longitude: 36.817,
        latitudeDelta: 0.8,
        longitudeDelta: 0.8,
        }}
        ref={map}
        provider={PROVIDER_GOOGLE}
        >
            {properties.map(property => (<CustomMarker property={property}
                    isSelected={property.id === selectedPropertyId} 
                    onPress={() => setSelectedPropertyId(property.id)}/>))}
        </MapView>
        <View style={{position:"absolute", bottom:55, left:0}}>
              <FlatList
                ref={flatList}
                data={properties}
                renderItem={({item}) => <PropertyCarouselItem property={item} />}
                horizontal
                showsHorizontalScrollIndicator={false}
                snapToInterval={width-60}
                snapToAlignment={"center"}
                decelerationRate={"fast"}
                viewabilityConfig={viewConfig.current}
                onViewableItemsChanged={onViewChanged.current}
              />
            
        </View>
    </View>
  )
}

export default SearchResultsMap