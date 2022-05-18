import { View, Text, Image, useWindowDimensions } from 'react-native'
import React from 'react'
import styles from "./styles"


const PropertyCarouselItem = ({property}) => {
  const { height, width } = useWindowDimensions();
  console.warn(property)
  return (
    <View style={[styles.container, {width:width-60}]}>
      <View style={styles.innerContainer}>
        <Image style={styles.image}
          source={{uri:property.image}}/>
        <View style={{marginHorizontal:10, width:width/2-20}}>
          <Text style={styles.bedrooms}>{property.bedroom}-Bedroom {property.title}</Text>
          <Text style={styles.description} numberOfLines={2} >
          {property.description}
          </Text>
          <Text style={styles.prices}>
              <Text style={styles.newPrice}> ${property.newPrice} </Text>
              Per Year
          </Text>
        </View>
      </View>
    </View>
  )
};

export default PropertyCarouselItem;