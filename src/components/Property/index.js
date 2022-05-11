import { View, Text, Image } from 'react-native'
import React from 'react'
import styles from './styles'


const Property = ({property}) => {
    console.log(property)
  return (
    <View style={styles.container}>
      <Image style={styles.image}
        source={{uri:property.image}}/>
      <Text style={styles.bedrooms}>{property.bedroom}-Bedroom {property.title}</Text>
      <Text style={styles.description} numberOfLines={3} >
      {property.description}
      </Text>
      <Text style={styles.prices}>
          <Text style={styles.oldPrice}>${property.oldPrice} </Text>
          <Text style={styles.newPrice}> ${property.newPrice} </Text>
           Per Year
      </Text>
      <Text style={styles.totalPrice}>${property.totalPrice} Total</Text>
    </View>
  )
};

export default Property